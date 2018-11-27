package am.victor.newsapp.fragments

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import am.victor.newsapp.adapters.NewsRecyclerViewAdapter
import am.victor.newsapp.fragments.dummy.DummyContent
import am.victor.newsapp.fragments.dummy.DummyContent.DummyItem
import am.victor.newsapp.viewmodels.SharedViewModel
import am.victor.newsappkotlin.R
import am.victor.newsappkotlin.activities.NewsDetailsActivity
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders

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
class SavedNewsFragment : Fragment() {
    // TODO: Customize parameters
    private var mColumnCount = 1
    private var mListener: OnListFragmentInteractionListener? = null
    private lateinit var sharedViewModel: SharedViewModel
    private lateinit var newsRecyclerViewAdapter: NewsRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val arguments = arguments
        if (arguments != null) {
            mColumnCount = arguments.getInt(ARG_COLUMN_COUNT)
        }

        sharedViewModel = ViewModelProviders.of(this).get(SharedViewModel::class.java)
        sharedViewModel.getUsers().observe(this, Observer<List<DummyItem>> { _ ->
            newsRecyclerViewAdapter.notifyDataSetChanged()
        })

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater!!.inflate(R.layout.fragment_news_list, container, false)

        // Set the adapter
        if (view is RecyclerView) {
            val context = view.getContext()
            val recyclerView = view as RecyclerView
            recyclerView.setLayoutManager(LinearLayoutManager(context))
            newsRecyclerViewAdapter = NewsRecyclerViewAdapter(
                DummyContent.ITEMS,
                null, // TODO: Change this later
                { newsItem: DummyItem -> newsItemClicked(newsItem) }
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
        fun newInstance(columnCount: Int): SavedNewsFragment {
            val fragment = SavedNewsFragment()
            val args = Bundle()
            args.putInt(ARG_COLUMN_COUNT, columnCount)
            fragment.arguments = args
            return fragment
        }
    }

    private fun newsItemClicked(newsItem: DummyItem) {
        val detailIntent = NewsDetailsActivity.newIntent(activity!!.baseContext, newsItem)
        startActivity(detailIntent)
    }
}
