package `in`.track.grabnews.utils

import `in`.track.grabnews.utils.extension.getParentActivity
import android.view.View
import android.webkit.WebView
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import android.webkit.WebViewClient



@BindingAdapter("mutableVisibility")
fun setMutableVisibility(view: View, visibility: MutableLiveData<Int>?) {
    val parent: AppCompatActivity? = view.getParentActivity()
    if (parent != null && visibility != null) {
        visibility.observe(parent, Observer { value -> view.visibility = value ?: View.VISIBLE })
    }
}

@BindingAdapter("adapter")
fun setAdapter(view: RecyclerView, adapter: RecyclerView.Adapter<*>) {
    view.adapter = adapter
}

@BindingAdapter("mutableText")
fun setMutableText(view: TextView, text: String?) {
    val parentActivity: AppCompatActivity? = view.getParentActivity()
    if (parentActivity != null && text != null) {
        view.text = text
    }
}

@BindingAdapter("imageUrl")
fun setImageUrl(view: ImageView, url: String?) {
    val parentActivity: AppCompatActivity? = view.getParentActivity()
    val requestOptions = RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL)
    if (parentActivity != null && url != null) {
            Glide.with(view.context).load(url).apply(requestOptions).transform(
                RoundedCorners(20)
            ).into(view)
    }
}

@BindingAdapter("setWebViewClient")
fun setWebViewClient(view: WebView, client: WebViewClient) {
    view.webViewClient = client
}

@BindingAdapter("webUrl")
fun setWebViewUrl(view: WebView, url: String?){
    val parentActivity: AppCompatActivity? = view.getParentActivity()
    if (parentActivity != null && url != null) {
        view.loadUrl(url)
    }
}

