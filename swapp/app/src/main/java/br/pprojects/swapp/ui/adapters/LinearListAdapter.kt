package br.pprojects.swapp.ui.adapters

import android.app.AlertDialog
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.pprojects.swapp.models.Character
import br.pprojects.swapp.R
import kotlinx.android.synthetic.main.character_item.view.*

class LinearListAdapter(var context: Context, var characters: List<Character>, var itemClick: () -> Unit) :
    RecyclerView.Adapter<LinearListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.character_item, parent, false)
        return ViewHolder(view, context, itemClick)
    }

    override fun getItemCount(): Int {
        return characters.size
    }

    override fun onBindViewHolder(holder: ViewHolder, pos: Int) {
        holder.bind(characters[pos])
    }

    class ViewHolder(var item: View, var context: Context, var itemClick: () -> Unit ) : RecyclerView.ViewHolder(item)  {
        private var isFavorite = false

        fun bind(character: Character){
            item.tv_name.text = character.name
            item.tv_mass.text = character.mass.toString()
            item.tv_height.text = character.height.toString()

            item.cl_character.setOnClickListener {
                //todo fragment list click
               itemClick()
            }

            item.iv_favorite.setOnClickListener {
                if (isFavorite) {
                    item.iv_favorite.setImageDrawable(context.getDrawable(R.drawable.ic_star_outline))
                    isFavorite = false
                } else {
                    item.iv_favorite.setImageDrawable(context.getDrawable(R.drawable.ic_star_full))
                    isFavorite = true
                }
            }


            if(character.gender == "M") item.iv_gender.setImageDrawable(context.getDrawable(R.drawable.ic_male))
            else if(character.gender == "F") item.iv_gender.setImageDrawable(context.getDrawable(R.drawable.ic_female))
        }
    }
}
