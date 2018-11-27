package am.victor.newsapp.activities

import am.victor.newsapp.adapters.TabPagerAdapter
import am.victor.newsapp.fragments.NewsFragment
import am.victor.newsapp.fragments.SavedNewsFragment
import am.victor.newsapp.fragments.dummy.DummyContent
import am.victor.newsappkotlin.R
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TabLayout
import kotlinx.android.synthetic.main.activity_news_feed.*

class NewsFeedActivity : AppCompatActivity(), NewsFragment.OnListFragmentInteractionListener,
    SavedNewsFragment.OnListFragmentInteractionListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_feed)
//        setSupportActionBar(toolbar)

        configureTabLayout()
    }

    private fun configureTabLayout() {

        tab_layout.addTab(tab_layout.newTab().setText(R.string.news))
        tab_layout.addTab(tab_layout.newTab().setText(R.string.saved_news))

        val adapter = TabPagerAdapter(
            supportFragmentManager,
            tab_layout.tabCount
        )
        pager.adapter = adapter

        pager.addOnPageChangeListener(
            TabLayout.TabLayoutOnPageChangeListener(tab_layout)
        )

        tab_layout.addOnTabSelectedListener(object :

            TabLayout.OnTabSelectedListener {

            override fun onTabSelected(tab: TabLayout.Tab) {
                pager.currentItem = tab.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {

            }

            override fun onTabReselected(tab: TabLayout.Tab) {

            }

        })
    }

    override fun onListFragmentInteraction(item: DummyContent.DummyItem) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}
