package br.pprojects.swapp.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import br.pprojects.swapp.R
import br.pprojects.swapp.ui.adapters.ViewPagerAdapter
import br.pprojects.swapp.ui.fragments.CharactersFragment
import br.pprojects.swapp.ui.fragments.PlanetsFragment
import br.pprojects.swapp.ui.fragments.SpeciesFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        val fragments = listOf<Fragment>(CharactersFragment.newInstance(), SpeciesFragment.newInstance(), PlanetsFragment.newInstance())

        val viewPager = findViewById<ViewPager>(R.id.vp_main)
        viewPager.adapter = ViewPagerAdapter(fragments, supportFragmentManager)

        val tabLayout = findViewById<TabLayout>(R.id.tl_main)
        tabLayout.setupWithViewPager(viewPager)
    }
}
