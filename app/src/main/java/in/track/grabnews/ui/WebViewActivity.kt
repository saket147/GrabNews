package `in`.track.grabnews.ui

import `in`.track.grabnews.R
import `in`.track.grabnews.databinding.ActivityWebViewBinding
import `in`.track.grabnews.injection.ViewModelFactory
import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders

class WebViewActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWebViewBinding

    private lateinit var webViewViewModal: WebViewViewModal
    private var url: String? = null

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_web_view)
        url = intent?.getStringExtra("url")
        webViewViewModal = ViewModelProviders.of(this, ViewModelFactory(this, url = url))
            .get(WebViewViewModal::class.java)

        binding.webView.settings.javaScriptEnabled = true
        binding.webView.settings.loadWithOverviewMode = true
        binding.webView.settings.useWideViewPort = true

        binding.viewModel = webViewViewModal
    }
}
