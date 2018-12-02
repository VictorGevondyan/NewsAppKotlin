package am.victor.newsapp.fragments

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import am.victor.newsapp.adapters.NewsRecyclerViewAdapter
import am.victor.newsapp.fragments.dummy.DummyContent.DummyItem
import am.victor.newsapp.models.NewsItem
import am.victor.newsapp.viewmodels.NewsViewModel
import am.victor.newsappkotlin.R
import am.victor.newsappkotlin.activities.NewsDetailsActivity
import am.victor.newsappkotlin.models.NewsResponseWrapper
import am.victor.newsappkotlin.repositories.NewsRepository
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.util.Log
import android.support.v7.widget.DividerItemDecoration




/**
 * A fragment representing a list of Items.
 *
 *
 * Activities containing this fragment MUST implement the [OnListFragmentInteractionListener]
 * interface.
 */
/**
 * Mandatory empty constructor for the fragment manager to instantiate the
 * fragment (e.g. upon screen orientation changes).
 */
class NewsFragment : Fragment() {

    // TODO: Customize parameters
    private var mColumnCount = 1
    private var mListener: OnListFragmentInteractionListener? = null
    private lateinit var newsViewModel: NewsViewModel
    private lateinit var newsRecyclerViewAdapter: NewsRecyclerViewAdapter
    private lateinit var newsResponseWrapper: NewsResponseWrapper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // To avoid "Smart cast is impossible"
        val arguments = arguments;
        if (arguments != null) {
            mColumnCount = arguments.getInt(ARG_COLUMN_COUNT)
        }

        newsViewModel = ViewModelProviders.of(this).get(NewsViewModel::class.java)
        newsViewModel.getNews().observe(this, Observer<List<NewsItem>> { _ ->
            newsRecyclerViewAdapter.notifyDataSetChanged()
        })

        val newsRepository = NewsRepository(activity!!.baseContext)
        val newsService = newsRepository.newsService
        val newsDatabase = newsRepository.newsDatabase
        val newsDao = newsDatabase.newsDao()
        val response = newsRepository.getNews()
        Log.d("jjl", "djskd")
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
            var initialList = arrayListOf<NewsItem>()
            newsRecyclerViewAdapter = NewsRecyclerViewAdapter(
                initialList,
                mListener,
                { newsItem: NewsItem -> newsItemClicked(newsItem) }
            )
            recyclerView.adapter = newsRecyclerViewAdapter
        }

        return view
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is OnListFragmentInteractionListener) {
            mListener = context
        } else {
            throw RuntimeException(context!!.toString() + " must implement OnListFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        mListener = null
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments](http://developer.android.com/training/basics/fragments/communicating.html) for more information.
     */
    interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onListFragmentInteraction(item: DummyItem)
    }

    companion object {

        // TODO: Customize parameter argument names
        private val ARG_COLUMN_COUNT = "column-count"

        // TODO: Customize parameter initialization
        fun newInstance(columnCount: Int): NewsFragment {
            val fragment = NewsFragment()
            val args = Bundle()
            args.putInt(ARG_COLUMN_COUNT, columnCount)
            fragment.arguments = args
            return fragment
        }
    }

    private fun newsItemClicked(newsItem: NewsItem) {
        val detailIntent = NewsDetailsActivity.newIntent(activity!!.baseContext, newsItem)
        startActivity(detailIntent)
    }

}
