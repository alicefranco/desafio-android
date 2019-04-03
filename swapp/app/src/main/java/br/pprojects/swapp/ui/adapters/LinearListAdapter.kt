package br.pprojects.swapp.ui.adapters

import android.app.AlertDialog
import android.content.Context
import android.support.v7.recyclerview.extensions.ListAdapter
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.pprojects.swapp.models.Character
import br.pprojects.swapp.R
import kotlinx.android.synthetic.main.character_item.view.*

class LinearListAdapter(var context: Context, var itemClick: (id: Int) -> Unit,
                        var itemClickFavorite: (id: Int, value: Boolean) -> Unit) :
    ListAdapter<Character, LinearListAdapter.ViewHolder>(
        object: DiffUtil.ItemCallback<Character>(){
            override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean = oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean = oldItem == newItem
        }
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.character_item, parent, false)
        return ViewHolder(view, context, itemClick, itemClickFavorite)
    }

    override fun onBindViewHolder(holder: ViewHolder, pos: Int) {
        holder.bind(getItem(pos))
    }

    class ViewHolder(var item: View, var context: Context, var itemClick: (id: Int) -> Unit,
                     var itemClickFavorite: (id: Int, value: Boolean) -> Unit ) : RecyclerView.ViewHolder(item)  {

        fun bind(character: Character){
            item.tv_name.text = character.name
            item.tv_mass.text = character.mass + " kg"
            item.tv_height.text = character.height + " cm"

            item.cl_character.setOnClickListener {
                //todo fragment list click
               itemClick(character.id ?: 0)
            }

            item.iv_favorite.setOnClickListener {
                if (character.isFavorite) {
                    item.iv_favorite.setImageDrawable(context.getDrawable(R.drawable.ic_star_outline))
                    character.isFavorite = false
                    itemClickFavorite(character.id ?: 0, false)
                } else {
                    item.iv_favorite.setImageDrawable(context.getDrawable(R.drawable.ic_star_full))
                    character.isFavorite = true
                    itemClickFavorite(character.id ?: 0, true)
                }
            }


            if(character.gender == "male")
                item.iv_gender.setImageDrawable(context.getDrawable(R.drawable.ic_male))
            else if(character.gender == "female")
                item.iv_gender.setImageDrawable(context.getDrawable(R.drawable.ic_female))
        }
    }
}
