package br.pprojects.swapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import br.pprojects.swapp.R
import br.pprojects.swapp.models.Planet
import br.pprojects.swapp.ui.EndlessScrollListener
import br.pprojects.swapp.ui.adapters.PlanetsListAdapter
import br.pprojects.swapp.viewmodels.PlanetsViewModel
import kotlinx.android.synthetic.main.planets_fragment.*

class PlanetsFragment : Fragment() {
    private lateinit var viewModel: PlanetsViewModel
    private var adapter: PlanetsListAdapter? = null
    private var firstClick = true

    companion object {
        const val TAG = "PLANETS"

        fun newInstance() : PlanetsFragment {
            return PlanetsFragment().apply {
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.planets_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val  manager = LinearLayoutManager(activity)
        adapter = PlanetsListAdapter(context!!, {})
        rv_planets.layoutManager = manager
        rv_planets.adapter = adapter
        rv_planets.addOnScrollListener(object : EndlessScrollListener(manager){
            override fun loadMore() {
                viewModel.getPlanets()
                viewModel.planets.observe(this@PlanetsFragment, Observer<List<Planet>> { addedPlanets ->
                    (adapter as PlanetsListAdapter).submitList(addedPlanets)
                    (adapter as PlanetsListAdapter).notifyDataSetChanged()
                })
            }
        })

        viewModel = ViewModelProviders.of(this).get(PlanetsViewModel::class.java)
        viewModel.firstPlanets.observe(this, Observer<List<Planet>> {
            val planets = it ?: listOf()
            adapter?.submitList(planets)
        })
    }


//    val itemClick: (id: Int) -> Unit = {
//        fl_details.visible()
//        fab_favorites.gone()
//        val fragment = CharacterDetailsFragment.newInstance(it, closeFragment)
//        if(firstClick) {
//            addFragment(R.id.fl_details, fragment, CharacterDetailsFragment.TAG)
//            firstClick = false
//        }
//        else
//            replaceFragment(R.id.fl_details, fragment, CharacterDetailsFragment.TAG)
//    }

}
