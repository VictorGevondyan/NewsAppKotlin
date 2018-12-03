package am.victor.newsapp.fragments

import am.victor.newsapp.adapters.NewsRecyclerViewAdapter
import am.victor.newsapp.models.NewsItem
import am.victor.newsapp.viewmodels.NewsViewModel
import am.victor.newsappkotlin.R
import am.victor.newsappkotlin.activities.NewsDetailsActivity
import am.victor.newsappkotlin.models.NewsResponseWrapper
import am.victor.newsappkotlin.viewmodels.NewsViewModelFactory
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup


/**
 * A fragment representing a list of Items.
 *
 *
 * Activities containing this fragment MUST implement the [OnListFragmentInteractionListener]
 * interface.
 */
class NewsFragment : Fragment() {

    private var areOnlineNews = true
    private lateinit var newsViewModel: NewsViewModel
    private lateinit var newsRecyclerViewAdapter: NewsRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // To avoid "Smart cast is impossible"
        val arguments = arguments;
        if (arguments != null) {
            areOnlineNews = arguments.getBoolean(ARG_ONLINE_NEWS)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_news_list, container, false)

        // Set the adapter
        if (view is RecyclerView) {
            val context = view.getContext()
            val recyclerView = view
            recyclerView.addItemDecoration(
                DividerItemDecoration(
                    context,
                    DividerItemDecoration.VERTICAL
                )
            )

            var initialList = listOf<NewsItem>()
            newsRecyclerViewAdapter = NewsRecyclerViewAdapter(
                initialList,
                { newsItem: NewsItem -> newsItemClicked(newsItem) }
            )

            recyclerView.adapter = newsRecyclerViewAdapter

        }

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val viewModelFactory = NewsViewModelFactory(requireContext(), areOnlineNews)
        newsViewModel = ViewModelProviders.of(requireActivity(), viewModelFactory)
            .get(NewsViewModel::class.java)

        newsViewModel.newsObject.observe(this, Observer<Any> { obtainedObject ->

            if (obtainedObject != null) {

                val newsList = if (obtainedObject is NewsResponseWrapper) {
                    obtainedObject.newsList
                } else {
                    obtainedObject as List<NewsItem>
                }

                newsRecyclerViewAdapter.addAll(newsList)

            }

        })
    }

    companion object {

        private val ARG_ONLINE_NEWS = "areOnlineNews"

        fun newInstance(areOnlineNews: Boolean): NewsFragment {
            val fragment = NewsFragment()
            val args = Bundle()
            args.putBoolean(ARG_ONLINE_NEWS, areOnlineNews)
            fragment.arguments = args
            return fragment
        }

    }

    private fun newsItemClicked(newsItem: NewsItem) {
        val detailIntent = NewsDetailsActivity.newIntent(activity!!.baseContext, newsItem)
        startActivity(detailIntent)
    }

}
