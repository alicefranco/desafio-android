package br.pprojects.swapp.ui.fragments

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.pprojects.swapp.App
import br.pprojects.swapp.ui.adapters.LinearListAdapter
import br.pprojects.swapp.R
import br.pprojects.swapp.data.database.CharacterDao
import br.pprojects.swapp.data.webservice.CharacterWebservice
import br.pprojects.swapp.models.Character
import br.pprojects.swapp.models.CharacterWS
import br.pprojects.swapp.repository.CharacterRepository
import br.pprojects.swapp.ui.EndlessScrollListener
import br.pprojects.swapp.viewmodels.CharactersViewModel
import kotlinx.android.synthetic.main.planets_fragment.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class CharactersFragment : Fragment() {
    companion object {
        const val TAG = "CHARACTERS"

        fun newInstance() : CharactersFragment {
            return CharactersFragment().apply {
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.characters_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        var viewModel = ViewModelProviders.of(this).get(CharactersViewModel::class.java)

        viewModel.firstCharacters.observe(this, Observer<List<Character>> {
            var characters = it ?: listOf()
            var  manager = LinearLayoutManager(activity)
            var adapter = LinearListAdapter(context!!, {})
            adapter?.submitList(characters)
            rv_characters.layoutManager = manager
            rv_characters.adapter = adapter
            rv_characters.addOnScrollListener(object : EndlessScrollListener(manager){
                override fun loadMore() {
                    viewModel.getCharacters()
                    viewModel.characters.observe(this@CharactersFragment, Observer<List<Character>> { addedUsers ->
                        adapter.submitList(addedUsers)
                        adapter.notifyDataSetChanged()
                    })
                }
            })
        })
    }

}
