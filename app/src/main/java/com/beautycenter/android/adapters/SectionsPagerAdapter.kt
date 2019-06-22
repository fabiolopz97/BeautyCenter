package com.beautycenter.android.adapters

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.beautycenter.android.R
import com.beautycenter.android.fragment.ServicesFragment
import com.beautycenter.android.fragment.Test1Fragment
import com.beautycenter.android.fragment.Test2Fragment

private val TAB_TITLES = arrayOf(
    R.string.tab_text_1,
    R.string.tab_text_2,
    R.string.tab_text_3
)

class SectionsPagerAdapter(private val context: Context, fm: FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment =
        when(position){
            0 -> ServicesFragment()
            1 -> Test1Fragment()
            2 -> Test2Fragment()
            else -> ServicesFragment()
        }

    override fun getPageTitle(position: Int): CharSequence? {
        return context.resources.getString(TAB_TITLES[position])
    }

    override fun getCount(): Int {
        // Show all pages.
        return TAB_TITLES.size
    }
}