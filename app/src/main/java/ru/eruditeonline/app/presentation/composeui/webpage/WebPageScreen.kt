package ru.eruditeonline.app.presentation.composeui.webpage

import android.annotation.SuppressLint
import android.net.Uri
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.net.toUri
import ru.eruditeonline.app.data.model.LoadableState
import ru.eruditeonline.app.presentation.composeui.base.ObserveDestinations
import ru.eruditeonline.app.presentation.composeui.base.appViewModel
import ru.eruditeonline.app.presentation.composeui.views.NavigationIcon
import ru.eruditeonline.app.presentation.composeui.views.StateFlipperView
import ru.eruditeonline.app.presentation.composeui.views.StateFlipperViewErrorView
import ru.eruditeonline.app.presentation.composeui.views.StateFlipperViewLoadingView
import ru.eruditeonline.app.presentation.ui.webpage.WebPageViewModel
import ru.eruditeonline.app.data.model.base.WebPage as WebPageData

private const val ERUDITE_DOMAIN = "erudit-online.ru"
private const val WEB_VIEW_BASE_URL = "https://erudit-online.ru"
private const val WEB_VIEW_MIME_TYPE = "text/html; charset=utf-8"
private const val WEB_VIEW_ENCODING = "UTF-8"

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InfoScreen(path: String, viewModel: WebPageViewModel = appViewModel()) {
    viewModel.ObserveDestinations()
    val pageState by viewModel.webPageLiveData.observeAsState(LoadableState.Loading())
    var toolbarTitle by rememberSaveable { mutableStateOf("") }

    LaunchedEffect(path) {
        // TODO тут должен быть call operations, но надо переписать ВМ, чтобы они создавались по одной на инстанс экрана
        viewModel.loadWebPage(path)
    }

    LaunchedEffect(pageState) {
        val page = (pageState as? LoadableState.Success)?.data ?: return@LaunchedEffect
        toolbarTitle = page.title
    }

    BackHandler {
        viewModel.onBackPressed()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = toolbarTitle) },
                navigationIcon = { NavigationIcon(viewModel::onBackPressed) },
            )
        },
        contentWindowInsets = WindowInsets.systemBars,
    ) { innerPadding ->
        StateFlipperView(
            state = pageState,
            onRetryClick = {
                viewModel.loadWebPage(viewModel.getCurrentPath() ?: path, skipStack = true)
            },
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
        ) { page ->
            WebPageView(
                page = page,
                modifier = Modifier.fillMaxSize(),
                onInternalLinkClick = { internalPath ->
                    viewModel.loadWebPage(internalPath)
                },
                onExternalLinkClick = viewModel::openBrowser,
            )
        }
    }
}

@SuppressLint("SetJavaScriptEnabled")
@Composable
private fun WebPageView(
    page: WebPageData,
    modifier: Modifier = Modifier,
    onInternalLinkClick: (String) -> Unit,
    onExternalLinkClick: (Uri) -> Unit,
) {
    var webViewRef by remember { mutableStateOf<WebView?>(null) }
    var lastContentKey by remember { mutableStateOf("") }
    var pageLoadState by remember(page.path) {
        mutableStateOf<LoadableState<Unit>>(LoadableState.Loading())
    }

    DisposableEffect(Unit) {
        onDispose {
            webViewRef?.apply {
                stopLoading()
                webViewClient = object : WebViewClient() {}
                destroy()
            }
        }
    }

    Box(modifier = modifier) {
        AndroidView(
            modifier = Modifier.fillMaxSize(),
            factory = { context ->
                WebView(context).apply {
                    settings.apply {
                        javaScriptEnabled = true
                        domStorageEnabled = true
                        javaScriptCanOpenWindowsAutomatically = true
                    }
                    webViewClient = object : WebViewClient() {
                        override fun onPageFinished(view: WebView?, url: String?) {
                            super.onPageFinished(view, url)
                            pageLoadState = LoadableState.Success(Unit)
                        }

                        @Deprecated("Deprecated in Java")
                        override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                            val uri = runCatching { url?.toUri() }.getOrNull() ?: return false
                            return handleUri(uri, onInternalLinkClick, onExternalLinkClick)
                        }

                        override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
                            if (request?.isForMainFrame == false) return false
                            val uri = request?.url ?: return false
                            return handleUri(uri, onInternalLinkClick, onExternalLinkClick)
                        }

                        override fun onReceivedError(
                            view: WebView?,
                            request: WebResourceRequest?,
                            error: WebResourceError?,
                        ) {
                            super.onReceivedError(view, request, error)
                            if (request?.isForMainFrame == true) {
                                pageLoadState = LoadableState.Error(IllegalStateException("web page rendering failed"))
                            }
                        }
                    }
                }.also {
                    webViewRef = it
                }
            },
            update = { webView ->
                val contentKey = "${page.path}_${page.content.hashCode()}"
                if (lastContentKey != contentKey) {
                    pageLoadState = LoadableState.Loading()
                    lastContentKey = contentKey
                    webView.loadDataWithBaseURL(
                        WEB_VIEW_BASE_URL,
                        page.content,
                        WEB_VIEW_MIME_TYPE,
                        WEB_VIEW_ENCODING,
                        null,
                    )
                }
            },
        )

        when (val state = pageLoadState) {
            is LoadableState.Loading -> StateFlipperViewLoadingView()
            is LoadableState.Error -> StateFlipperViewErrorView(
                error = state.error,
                onRetryClick = {
                    pageLoadState = LoadableState.Loading()
                    webViewRef?.reload()
                },
            )
            is LoadableState.Success -> Unit
        }
    }
}

private fun handleUri(
    uri: Uri,
    onInternalLinkClick: (String) -> Unit,
    onExternalLinkClick: (Uri) -> Unit,
): Boolean {
    if (uri.host == ERUDITE_DOMAIN) {
        val path = uri.toString().removePrefix(WEB_VIEW_BASE_URL)
        if (path.isBlank()) return false
        onInternalLinkClick(path)
        return true
    } else {
        onExternalLinkClick(uri)
        return true
    }
}
