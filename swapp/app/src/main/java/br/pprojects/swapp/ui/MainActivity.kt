package br.pprojects.swapp.ui

import android.os.Bundle
import android.widget.TabWidget
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import br.pprojects.swapp.App
import br.pprojects.swapp.R
import br.pprojects.swapp.data.database.AppDatabase
import br.pprojects.swapp.ui.adapters.ViewPagerAdapter
import br.pprojects.swapp.ui.fragments.CharactersFragment
import br.pprojects.swapp.ui.fragments.PlanetsFragment
import br.pprojects.swapp.ui.fragments.SpeciesFragment
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        App.database = AppDatabase.getAppDataBase(this)

        val fragmentTitles = listOf(CharactersFragment.TAG, PlanetsFragment.TAG, SpeciesFragment.TAG)
        val fragments = listOf<Fragment>(CharactersFragment.newInstance(), PlanetsFragment.newInstance(), SpeciesFragment.newInstance())

        val viewPager = findViewById<ViewPager>(R.id.vp_main)
        viewPager.adapter = ViewPagerAdapter(fragmentTitles, fragments, supportFragmentManager)

        val tabLayout = findViewById<TabLayout>(R.id.tl_main)
        tabLayout.setupWithViewPager(viewPager)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}
