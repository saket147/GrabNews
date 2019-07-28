package `in`.track.grabnews.ui

import `in`.track.grabnews.base.BaseViewModel
import android.view.View
import androidx.lifecycle.MutableLiveData
import android.webkit.WebView
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebViewClient



class WebViewViewModal(private val url: String): BaseViewModel() {
    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()
    var pageUrl: String = url

    init {
        loadingVisibility.value = View.VISIBLE
        getWebViewClient()
    }

    private inner class Client : WebViewClient() {

        override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
            loadingVisibility.value = View.VISIBLE
            return super.shouldOverrideUrlLoading(view, request)
        }

        override fun onReceivedError(view: WebView, request: WebResourceRequest, error: WebResourceError) {
            super.onReceivedError(view, request, error)
            loadingVisibility.value = View.VISIBLE
        }

        override fun onPageFinished(view: WebView, url: String) {
            super.onPageFinished(view, url)
            loadingVisibility.value = View.GONE
        }
    }

    fun getWebViewClient(): WebViewClient{
        return Client()
    }

}