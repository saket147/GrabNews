package `in`.track.grabnews.ui.adapter

import `in`.track.grabnews.R
import `in`.track.grabnews.model.Article
import `in`.track.grabnews.databinding.ItemHeadlineBinding
import `in`.track.grabnews.ui.activity.WebViewActivity
import `in`.track.grabnews.utils.Utils
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import okhttp3.internal.Util


class HeadLinesAdapter: RecyclerView.Adapter<HeadLinesAdapter.ViewHolder>() {
    private lateinit var articleList:List<Article>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemHeadlineBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context),
            R.layout.item_headline, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(articleList[position])
        holder.itemView.setOnClickListener{
            val intent = Intent(holder.itemView.context, WebViewActivity::class.java)
            intent.putExtra("url", articleList[position].url)
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return if(::articleList.isInitialized) articleList.size else 0
    }

    fun updateArticleList(articleList:List<Article>){
        this.articleList = articleList
        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: ItemHeadlineBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(article: Article){
            article.publishedAt = Utils.convertTime(article.publishedAt)
            binding.articles = article
        }
    }
}