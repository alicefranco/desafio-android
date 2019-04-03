package br.pprojects.swapp.ui.adapters

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

class ViewPagerAdapter(private var fragmentTitles: List<String>, private var fragments: List<Fragment>, supportFragmentManager: FragmentManager) :
        FragmentPagerAdapter(supportFragmentManager) {


    override fun getItem(position: Int): Fragment {
        return fragments[position]
    }

    override fun getCount(): Int {
        return fragments.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return fragmentTitles[position]
    }
}
