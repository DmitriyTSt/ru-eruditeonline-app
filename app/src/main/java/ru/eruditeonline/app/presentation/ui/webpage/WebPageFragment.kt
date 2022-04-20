package ru.eruditeonline.app.presentation.ui.webpage

import android.annotation.SuppressLint
import android.os.Bundle
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.eruditeonline.app.R
import ru.eruditeonline.app.data.model.LoadableState
import ru.eruditeonline.app.data.model.base.WebPage
import ru.eruditeonline.app.databinding.FragmentWebPageBinding
import ru.eruditeonline.app.presentation.extension.appViewModels
import ru.eruditeonline.app.presentation.extension.fitTopInsetsWithPadding
import ru.eruditeonline.app.presentation.navigation.observeNavigationCommands
import ru.eruditeonline.app.presentation.ui.base.BaseFragment

private const val WEB_VIEW_BASE_URL = "https://erudit-online.ru"
private const val WEB_VIEW_MIME_TYPE = "text/html; charset=utf-8"
private const val WEB_VIEW_ENCODING = "UTF-8"

class WebPageFragment : BaseFragment(R.layout.fragment_web_page) {
    private val binding by viewBinding(FragmentWebPageBinding::bind)
    private val viewModel: WebPageViewModel by appViewModels()
    private val args: WebPageFragmentArgs by navArgs()

    override fun callOperations() {
        viewModel.loadWebPage(args.path)
    }

    override fun setupLayout(savedInstanceState: Bundle?) = with(binding) {
        toolbar.fitTopInsetsWithPadding()
        toolbar.setNavigationOnClickListener {
            viewModel.navigateBack()
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

    @SuppressLint("SetJavaScriptEnabled")
    private fun setupWebView() = with(binding) {
        webView.settings.apply {
            javaScriptEnabled = true
            domStorageEnabled = true
        }
        webView.webViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                binding.stateViewFlipper.setState(LoadableState.Success(Unit))
            }

            override fun onReceivedError(view: WebView?, request: WebResourceRequest?, error: WebResourceError?) {
                super.onReceivedError(view, request, error)
                setErrorState()
            }

            override fun onReceivedError(view: WebView?, errorCode: Int, description: String?, failingUrl: String?) {
                super.onReceivedError(view, errorCode, description, failingUrl)
                setErrorState()
            }
        }
    }

    private fun setErrorState() {
        binding.stateViewFlipper.setState(LoadableState.Error<Unit>(IllegalStateException("load webview fail")))
    }

    private fun bindPage(page: WebPage) = with(binding) {
        toolbar.title = page.title
        webView.loadDataWithBaseURL(WEB_VIEW_BASE_URL, page.content, WEB_VIEW_MIME_TYPE, WEB_VIEW_ENCODING, null)
    }
}