package br.pprojects.swapp.ui.fragments

import android.graphics.drawable.Icon
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import br.pprojects.swapp.*
import br.pprojects.swapp.models.Character
import br.pprojects.swapp.ui.EndlessScrollListener
import br.pprojects.swapp.ui.adapters.CharacterListAdapter
import br.pprojects.swapp.viewmodels.CharactersViewModel
import kotlinx.android.synthetic.main.characters_fragment.*

class CharactersFragment : Fragment() {
    private lateinit var viewModel: CharactersViewModel
    private var adapter: CharacterListAdapter? = null
    private var firstClick = true


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

        val  manager = LinearLayoutManager(activity)
        adapter = CharacterListAdapter(context!!, itemClick, itemClickFavorite)
        rv_characters.layoutManager = manager
        rv_characters.adapter = adapter
        rv_characters.addOnScrollListener(object : EndlessScrollListener(manager){
            override fun loadMore() {
                if(viewModel.isFavoriteList == false) {
                    viewModel.getCharacters()
                    viewModel.characters.observe(this@CharactersFragment, Observer<List<Character>> { addedUsers ->
                        (adapter as CharacterListAdapter).submitList(addedUsers)
                        (adapter as CharacterListAdapter).notifyDataSetChanged()
                    })
                }
            }
        })

        viewModel = ViewModelProviders.of(this).get(CharactersViewModel::class.java)
        viewModel.firstCharacters.observe(this, Observer<List<Character>> {
            val characters = it ?: listOf()
            adapter?.submitList(characters)

        })




        fab_favorites.setOnClickListener{
            viewModel.isFavoriteList = !(viewModel.isFavoriteList)
            if(viewModel.isFavoriteList){
               // fab_favorites.setImageIcon(Icon.createWithResource(context, ic_star_full_white))
            }
            else{
                //fab_favorites.setImageIcon(Icon.createWithResource(context, ic_star_outline_white))
            }

            viewModel.showFavorites().observe(this, Observer<List<Character>> {
                adapter?.submitList(it)
            })
        }
    }


    val itemClick: (id: Int) -> Unit = {
        fl_details.visible()
        fab_favorites.gone()
        val fragment = CharacterDetailsFragment.newInstance(it, closeFragment)
        if(firstClick) {
            addFragment(R.id.fl_details, fragment, CharacterDetailsFragment.TAG)
            firstClick = false
        }
        else
            replaceFragment(R.id.fl_details, fragment, CharacterDetailsFragment.TAG)
    }

    val itemClickFavorite: (id: Int, value: Int) -> Unit = { id, value ->
        viewModel.updateFavorite(id, value)
    }

    val closeFragment: () -> Unit = {
        fl_details.gone()
        fab_favorites.visible()
        activity?.supportFragmentManager?.popBackStack()
    }
}
