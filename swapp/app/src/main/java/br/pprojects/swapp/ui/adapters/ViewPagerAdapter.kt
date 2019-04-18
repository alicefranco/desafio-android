package br.pprojects.swapp.ui.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class ViewPagerAdapter(private var fragmentTitles: List<String>, private var fragments: List<Fragment>, supportFragmentManager: FragmentManager) :
        FragmentPagerAdapter(supportFragmentManager) {


    override fun getItem(position: Int): androidx.fragment.app.Fragment? {
        return fragments[position]
    }

    override fun getCount(): Int {
        return fragments.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return fragmentTitles[position]
    }
}
