package br.pprojects.swapp.ui.adapters

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

class ViewPagerAdapter(private var fragments: List<Fragment>, supportFragmentManager: FragmentManager) :
    FragmentPagerAdapter(supportFragmentManager) {
    override fun getItem(position: Int): Fragment {
        return fragments[position]
    }

    override fun getCount(): Int {
        return fragments.size
    }

    //todo title tab
//    override fun getPageTitle(position: Int): CharSequence? {
//        return super.getPageTitle(position)
//    }
}
