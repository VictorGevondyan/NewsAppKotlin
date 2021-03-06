package am.victor.newsapp.adapters

import am.victor.newsapp.fragments.NewsFragment
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

/**
 * Created by victor on 11/26/18.
 */


class TabPagerAdapter(fm: FragmentManager, private var tabCount: Int) :
    FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment? {

        when (position) {
            0 -> return NewsFragment.newInstance(true)
            1 -> return NewsFragment.newInstance(false)
            else -> return null
        }
    }

    override fun getCount(): Int {
        return tabCount
    }

}