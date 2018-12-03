package am.victor.newsapp.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import am.victor.newsapp.models.NewsItem
import am.victor.newsappkotlin.R

/**
 * [RecyclerView.Adapter] that can display a [NewsItem]
 */
class NewsRecyclerViewAdapter(
    private var newsList: List<NewsItem>,
    val onNewsItemClickListener: (NewsItem) -> Unit
) : RecyclerView.Adapter<NewsRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_news, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(newsList[position], onNewsItemClickListener)
    }

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val mIdView: TextView
        val mContentView: TextView

        init {
            mIdView = mView.findViewById(R.id.id) as TextView
            mContentView = mView.findViewById(R.id.content) as TextView
        }

        fun bind(newsItem: NewsItem, onNewsItemClickListener: (NewsItem) -> Unit) {
            mIdView.text = newsItem.title
            mContentView.text = newsItem.publishedAt
            mView.setOnClickListener {
                onNewsItemClickListener(newsItem)
            }
        }

        override fun toString(): String {
            return super.toString() + " '" + mContentView.text + "'"
        }
    }

    override fun getItemCount(): Int {
        return newsList.size
    }

    fun addAll( updatedNewsList:List<NewsItem> ) {
        newsList = updatedNewsList
        notifyDataSetChanged()
    }
}
