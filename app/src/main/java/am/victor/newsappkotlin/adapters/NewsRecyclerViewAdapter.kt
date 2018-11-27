package am.victor.newsapp.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import am.victor.newsapp.fragments.NewsFragment.OnListFragmentInteractionListener
import am.victor.newsapp.fragments.dummy.DummyContent.DummyItem
import am.victor.newsapp.models.NewsItem
import am.victor.newsappkotlin.R

/**
 * [RecyclerView.Adapter] that can display a [DummyItem] and makes a call to the
 * specified [OnListFragmentInteractionListener].
 * TODO: Replace the implementation with code for your data type.
 */
class NewsRecyclerViewAdapter(
    private val newsList: MutableList<DummyItem>,
    private val mListener: OnListFragmentInteractionListener?,
    val onNewsItemClickListener: (DummyItem) -> Unit
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

        fun bind(dummyItem: DummyItem, onNewsItemClickListener: (DummyItem) -> Unit) {
            mIdView.text = dummyItem.id
            mContentView.text = dummyItem.content
            mView.setOnClickListener {
                onNewsItemClickListener(dummyItem)
            }
        }

        override fun toString(): String {
            return super.toString() + " '" + mContentView.text + "'"
        }
    }

    override fun getItemCount(): Int {
        return newsList.size
    }

    fun addItem(newsItem: DummyItem) {
        newsList.add(newsItem)
        notifyDataSetChanged()
    }
}
