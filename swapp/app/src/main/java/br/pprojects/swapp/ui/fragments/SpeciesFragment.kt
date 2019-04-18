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
import br.pprojects.swapp.models.Species
import br.pprojects.swapp.ui.EndlessScrollListener
import br.pprojects.swapp.ui.adapters.SpeciesListAdapter
import br.pprojects.swapp.viewmodels.SpeciesViewModel
import kotlinx.android.synthetic.main.species_fragment.*

class SpeciesFragment : Fragment() {
    private lateinit var viewModel: SpeciesViewModel
    private var adapter: SpeciesListAdapter? = null
    private var firstClick = true

    companion object {
        const val TAG = "CHARACTERS"

        fun newInstance() : SpeciesFragment {
            return SpeciesFragment().apply {
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.species_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val  manager = LinearLayoutManager(activity)
        adapter = SpeciesListAdapter(context!!, {})
        rv_species.layoutManager = manager
        rv_species.adapter = adapter
        rv_species.addOnScrollListener(object : EndlessScrollListener(manager){
            override fun loadMore() {
                viewModel.getSpecies()
                viewModel.species.observe(this@SpeciesFragment, Observer<List<Species>> { addedPlanets ->
                    (adapter as SpeciesListAdapter).submitList(addedPlanets)
                    (adapter as SpeciesListAdapter).notifyDataSetChanged()
                })
            }
        })

        viewModel = ViewModelProviders.of(this).get(SpeciesViewModel::class.java)
        viewModel.firstSpecies.observe(this, Observer<List<Species>> {
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