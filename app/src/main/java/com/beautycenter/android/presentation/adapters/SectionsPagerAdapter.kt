package com.beautycenter.android.presentation.adapters

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.beautycenter.android.R
import com.beautycenter.android.presentation.ui.mainnavigation.fragment.ServicesFragment
import com.beautycenter.android.presentation.ui.mainnavigation.fragment.Test1Fragment
import com.beautycenter.android.presentation.ui.mainnavigation.fragment.Test2Fragment

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