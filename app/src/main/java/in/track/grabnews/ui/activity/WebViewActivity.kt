package `in`.track.grabnews.ui.activity

import `in`.track.grabnews.R
import `in`.track.grabnews.databinding.ActivityWebViewBinding
import `in`.track.grabnews.injection.ViewModelFactory
import `in`.track.grabnews.ui.viewModel.WebViewViewModal
import `in`.track.grabnews.utils.ERROR
import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.snackbar.Snackbar

class WebViewActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWebViewBinding

    private lateinit var webViewViewModal: WebViewViewModal
    private var url: String? = null
    private var errorSnackbar: Snackbar? = null

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_web_view)
        url = intent?.getStringExtra("url")
        webViewViewModal = ViewModelProviders.of(this, ViewModelFactory(this, url = url))
            .get(WebViewViewModal::class.java)

        val webSettings = binding.webView.settings
        webSettings.javaScriptEnabled = true
        webSettings.loadWithOverviewMode = true
        webSettings.useWideViewPort = true

        binding.viewModel = webViewViewModal

        webViewViewModal.pageStatus.observe(this, Observer {
            value -> if (value == ERROR) showError() else hideError()
        })
    }

    //snackbar action
    private val errorClickListener = View.OnClickListener {
        finish()
    }

    private fun showError(){
        errorSnackbar = Snackbar.make(binding.root, R.string.website_loading_error, Snackbar.LENGTH_INDEFINITE)
        errorSnackbar?.setAction(R.string.ok, errorClickListener)
        errorSnackbar?.show()
    }

    private fun hideError(){
        errorSnackbar?.dismiss()
    }

}
