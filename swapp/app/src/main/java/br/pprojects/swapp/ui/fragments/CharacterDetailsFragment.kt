package br.pprojects.swapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import br.pprojects.swapp.R
import br.pprojects.swapp.gone
import br.pprojects.swapp.invisible
import br.pprojects.swapp.models.Character
import br.pprojects.swapp.models.Planet
import br.pprojects.swapp.models.Species
import br.pprojects.swapp.viewmodels.CharacterDetailsViewModel
import br.pprojects.swapp.visible
import kotlinx.android.synthetic.main.character_details_fragment.*

class CharacterDetailsFragment : Fragment() {
    private var id: Int? = null
    private var closeFragment: () -> Unit = {}

    companion object {
        const val TAG = "CharacterDetailsFragment"
        fun newInstance(id: Int, closeFragment: () -> Unit): CharacterDetailsFragment {
            return CharacterDetailsFragment().apply{
                this.id = id
                this.closeFragment = closeFragment
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.character_details_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        cl_character.setOnClickListener { }

        val viewModel = ViewModelProviders.of(this).get(CharacterDetailsViewModel::class.java)

        hideHomeworld()
        hideSpecies()

        viewModel.getCharacterDetails(id ?: 0)
        viewModel.character.observe(this, Observer<Character>{ character ->
            viewModel.getPlanet(character?.homeworld?.toInt() ?: 0)
            viewModel.planet.observe(this, Observer<Planet>{ planet ->
                tv_planet.text = planet?.name
                showPlanet()
            })

            viewModel.getSpecies(character?.species)
            viewModel.species.observe(this, Observer<Species>{ species ->
//                var speciesText = ""
//                var i = 0
//                while(i < species?.size ?: 0){
//                    speciesText = species?.get(i).toString() + "\n" + speciesText
//                    i++
//                }
                tv_species.text = species?.name
                showSpecies()
            })

            tv_name.text = character?.name
            tv_birth_year.text = character?.birthYear
            tv_eye_color.text = character?.eyeColor
            tv_hair_color.text = character?.hairColor
            tv_skin_color.text = character?.skinColor
            tv_height.text = character?.height
            tv_mass.text = character?.mass


            if (character?.favorited == 0) {
                iv_favorite.setImageDrawable(context?.getDrawable(R.drawable.ic_star_outline))
            } else {
                iv_favorite.setImageDrawable(context?.getDrawable(R.drawable.ic_star_full))
            }

            iv_favorite.setOnClickListener {
                if (character?.favorited == 1) {
                    iv_favorite.setImageDrawable(context?.getDrawable(R.drawable.ic_star_outline))
                    character?.favorited = 0
                    viewModel.updateFavorite(character?.id ?: 0, 0)
                } else {
                    iv_favorite.setImageDrawable(context?.getDrawable(R.drawable.ic_star_full))
                    character?.favorited = 1
                    viewModel.updateFavorite(character?.id ?: 0, 1)
                }
            }

            if(character?.gender == "male")
                iv_gender.setImageDrawable(context?.getDrawable(R.drawable.ic_male))
            else if(character?.gender == "female")
                iv_gender.setImageDrawable(context?.getDrawable(R.drawable.ic_female))

            tv_go_back.setOnClickListener{ closeFragment() }
        })



    }

    fun showSpecies(){
        tv_species.visible()
        pb_species.gone()
    }

    fun showPlanet(){
        tv_planet.visible()
        pb_planet.gone()
    }

    fun hideSpecies(){
        tv_species.invisible()
        pb_species.visible()
    }

    fun hideHomeworld(){
        tv_planet.invisible()
        pb_planet.visible()
    }
}
