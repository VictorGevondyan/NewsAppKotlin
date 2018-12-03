package am.victor.newsapp.activities

import am.victor.newsapp.adapters.TabPagerAdapter
import am.victor.newsapp.fragments.NewsFragment
import am.victor.newsapp.fragments.dummy.DummyContent
import am.victor.newsappkotlin.R
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_news_feed.*

class NewsFeedActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_feed)
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

}
