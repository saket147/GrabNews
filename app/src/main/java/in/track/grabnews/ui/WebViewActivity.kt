package `in`.track.grabnews.ui

import `in`.track.grabnews.R
import `in`.track.grabnews.databinding.ActivityWebViewBinding
import `in`.track.grabnews.model.Article
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil

class WebViewActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWebViewBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_web_view)
        binding.articles = intent?.getSerializableExtra("article") as Article
    }
}
