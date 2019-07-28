package `in`.track.grabnews.ui.viewModel

import `in`.track.grabnews.base.BaseViewModel
import `in`.track.grabnews.utils.LOADING
import `in`.track.grabnews.utils.SUCCESS
import android.view.View
import androidx.lifecycle.MutableLiveData
import android.webkit.WebView
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebViewClient

class WebViewViewModal(url: String): BaseViewModel() {
    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()
    val pageStatus: MutableLiveData<Int> = MutableLiveData()
    var pageUrl: String = url

    init {
        loadingVisibility.value = View.VISIBLE
        getWebViewClient()
    }


    //creating client for webView
    private inner class Client : WebViewClient() {

        override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
            loadingVisibility.value = View.VISIBLE
            pageStatus.value = LOADING
            return super.shouldOverrideUrlLoading(view, request)
        }

        override fun onReceivedError(view: WebView, request: WebResourceRequest, error: WebResourceError) {
            super.onReceivedError(view, request, error)
            loadingVisibility.value = View.GONE
//            pageStatus.value = ERROR
        }

        override fun onPageFinished(view: WebView, url: String) {
            super.onPageFinished(view, url)
            pageStatus.value = SUCCESS
            loadingVisibility.value = View.GONE
        }
    }

    fun getWebViewClient(): WebViewClient{
        return Client()
    }

}