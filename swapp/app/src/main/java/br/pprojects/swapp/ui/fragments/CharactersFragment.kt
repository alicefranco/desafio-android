package br.pprojects.swapp.ui.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.pprojects.swapp.models.Character
import br.pprojects.swapp.ui.adapters.LinearListAdapter
import br.pprojects.swapp.R
import kotlinx.android.synthetic.main.planets_fragment.*

class CharactersFragment : Fragment() {
    private var manager: LinearLayoutManager? = null
    private var adapter: LinearListAdapter? = null


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

        var characters = listOf(
            Character("Alice", "M", "15", "10"),
            Character("Alice", "F", "15", "10"),
            Character("Alice", "F", "15", "10"),
            Character("Alice", "M", "15", "10")
        )

        manager = LinearLayoutManager(activity)
        adapter = LinearListAdapter(context!!, characters, {})
        rv_characters.layoutManager = manager
        rv_characters.adapter = adapter


    }

}