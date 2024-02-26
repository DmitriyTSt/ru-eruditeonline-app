package ru.eruditeonline.app.presentation.ui.webpage

import android.annotation.SuppressLint
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.addCallback
import androidx.core.graphics.Insets
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.eruditeonline.app.R
import ru.eruditeonline.app.data.model.base.WebPage
import ru.eruditeonline.app.databinding.FragmentWebPageBinding
import ru.eruditeonline.app.presentation.extension.appViewModels
import ru.eruditeonline.app.presentation.extension.doOnApplyWindowInsets
import ru.eruditeonline.app.presentation.managers.InnerDeepLinkManager
import ru.eruditeonline.app.presentation.ui.base.BaseFragment
import ru.eruditeonline.architecture.presentation.model.LoadableState
import ru.eruditeonline.navigation.observeNavigationCommands
import javax.inject.Inject

private const val ERUDITE_DOMAIN = "erudit-online.ru"
private const val WEB_VIEW_BASE_URL = "https://erudit-online.ru"
private const val WEB_VIEW_MIME_TYPE = "text/html; charset=utf-8"
private const val WEB_VIEW_ENCODING = "UTF-8"

class WebPageFragment : BaseFragment(R.layout.fragment_web_page) {

    companion object {
        const val PERSONAL_DATA_PATH = "/personal-data.html"
        const val RESTORE_PASSWORD_PATH = "/forget_password.html"
    }

    private val binding by viewBinding(FragmentWebPageBinding::bind)
    private val viewModel: WebPageViewModel by appViewModels()
    private val args: WebPageFragmentArgs by navArgs()

    @Inject lateinit var innerDeepLinkManager: InnerDeepLinkManager

    override fun callOperations() {
        viewModel.loadWebPage(args.path)
    }

    override fun setupLayout(savedInstanceState: Bundle?) = with(binding) {
        setupInsets()
        toolbar.setNavigationOnClickListener {
            viewModel.onBackPressed()
        }
        activity?.onBackPressedDispatcher?.addCallback(viewLifecycleOwner) {
            viewModel.onBackPressed()
        }
        stateViewFlipper.setRetryMethod {
            webView.reload()
        }
        setupWebView()
    }

    override fun onBindViewModel() = with(viewModel) {
        observeNavigationCommands(viewModel)
        webPageLiveData.observe { state ->
            state.doOnLoading {
                binding.stateViewFlipper.setState(state)
            }
            state.doOnError {
                binding.stateViewFlipper.setState(state)
            }
            state.doOnSuccess { page ->
                bindPage(page)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        binding.webView.onResume()
    }

    override fun onPause() {
        binding.webView.onPause()
        super.onPause()
    }

    override fun onDestroyView() {
        binding.webView.stopLoading()
        // Чтобы коллбэки не вызывались после смерти вью
        binding.webView.webViewClient = object : WebViewClient() {}
        super.onDestroyView()
    }

    private fun setupInsets() = with(binding) {
        root.doOnApplyWindowInsets { _, insets, _ ->
            val windowInsets = insets.getInsets(WindowInsetsCompat.Type.systemBars() or WindowInsetsCompat.Type.ime())
            toolbar.updatePadding(top = windowInsets.top)
            root.updatePadding(bottom = windowInsets.bottom)
            WindowInsetsCompat.Builder().setInsets(
                WindowInsetsCompat.Type.systemBars(),
                Insets.of(
                    windowInsets.left,
                    0,
                    windowInsets.right,
                    0,
                )
            ).build()
        }
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun setupWebView() = with(binding) {
        webView.settings.apply {
            javaScriptEnabled = true
            domStorageEnabled = true
            javaScriptCanOpenWindowsAutomatically = true
        }
        webView.webViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                binding.stateViewFlipper.setState(LoadableState.Success(Unit))
                webView.scrollTo(0, viewModel.scrollYByPage.getOrDefault(viewModel.getCurrentPath(), 0))
                webView.setOnWebViewScrollChangeListener { _, _, scrollY, _, _ ->
                    viewModel.scrollYByPage[viewModel.getCurrentPath()] = scrollY
                    binding.appBarLayout.isLifted = scrollY > 0
                }
            }

            @Deprecated("Deprecated in Java")
            override fun onReceivedError(view: WebView?, errorCode: Int, description: String?, failingUrl: String?) {
                super.onReceivedError(view, errorCode, description, failingUrl)
                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
                    if (!isErrorToSkip(errorCode)) {
                        setErrorState()
                    }
                }
            }

            override fun onReceivedError(view: WebView?, request: WebResourceRequest?, error: WebResourceError?) {
                super.onReceivedError(view, request, error)
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (!isErrorToSkip(error?.errorCode)) {
                        setErrorState()
                    }
                }
            }

            override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
                openDeepLinkOrWebViewLoad(request?.url)
                return true
            }

            @Deprecated("Deprecated in Java")
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                openDeepLinkOrWebViewLoad(
                    try {
                        Uri.parse(url)
                    } catch (e: Exception) {
                        null
                    }
                )
                return true
            }
        }
    }

    private fun isErrorToSkip(errorCode: Int?): Boolean {
        return listOf(
            WebViewClient.ERROR_CONNECT,
            WebViewClient.ERROR_TIMEOUT,
            WebViewClient.ERROR_UNKNOWN,
        ).contains(errorCode)
    }

    private fun openDeepLinkOrWebViewLoad(uri: Uri?) {
        if (uri == null) return
        if (uri.host == ERUDITE_DOMAIN) {
            innerDeepLinkManager.resolveDeepLink(uri)?.let { viewModel.navigate(it) } ?: run {
                viewModel.loadWebPage(uri.toString().replace(WEB_VIEW_BASE_URL, ""))
            }
        } else {
            viewModel.openBrowser(uri)
        }
    }

    private fun setErrorState() {
        binding.stateViewFlipper.setState(LoadableState.Error<Unit>(IllegalStateException("load webview fail")))
    }

    private fun bindPage(page: WebPage) = with(binding) {
        webView.setOnWebViewScrollChangeListener(null)
        toolbar.title = page.title
        webView.loadDataWithBaseURL(
            WEB_VIEW_BASE_URL,
            page.content,
            WEB_VIEW_MIME_TYPE,
            WEB_VIEW_ENCODING,
            null,
        )
    }
}
