package br.pprojects.swapp.ui.adapters

import android.content.Context
import android.support.v7.recyclerview.extensions.ListAdapter
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.pprojects.swapp.models.Character
import br.pprojects.swapp.R
import br.pprojects.swapp.models.Planet
import kotlinx.android.synthetic.main.character_item.view.*

class PlanetsListAdapter(var context: Context, var itemClick: (id: Int) -> Unit,
                        var itemClickFavorite: (id: Int, value: Int) -> Unit) :
        ListAdapter<Planet, PlanetsListAdapter.ViewHolder>(
                object: DiffUtil.ItemCallback<Planet>(){
                    override fun areItemsTheSame(oldItem: Planet, newItem: Planet): Boolean = oldItem.id == newItem.id

                    override fun areContentsTheSame(oldItem: Planet, newItem: Planet): Boolean = oldItem == newItem
                }
        ) {

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.planet_item, parent, false)
        return ViewHolder(view, context, itemClick, itemClickFavorite)
    }

    override fun onBindViewHolder(holder: ViewHolder, pos: Int) {
        holder.bind(getItem(pos))
    }

    class ViewHolder(var item: View, var context: Context, var itemClick: (id: Int) -> Unit,
                     var itemClickFavorite: (id: Int, value: Int) -> Unit ) : RecyclerView.ViewHolder(item)  {

        fun bind(planet: Planet) {
            item.tv_name.text = planet.name
//            item.tv_mass.text = planet.mass + " kg"
//            item.tv_height.text = planet.height + " cm"

            item.cl_character.setOnClickListener {
                //todo fragment list click
                itemClick(planet.id ?: 0)
            }

//            if (character.favorited == 0) {
//                item.iv_favorite.setImageDrawable(context.getDrawable(R.drawable.ic_star_outline))
//            } else {
//                item.iv_favorite.setImageDrawable(context.getDrawable(R.drawable.ic_star_full))
//            }

//            item.iv_favorite.setOnClickListener {
//                if (character.favorited == 1) {
//                    item.iv_favorite.setImageDrawable(context.getDrawable(R.drawable.ic_star_outline))
//                    character.favorited = 0
//                    itemClickFavorite(character.id ?: 0, 0)
//                } else {
//                    item.iv_favorite.setImageDrawable(context.getDrawable(R.drawable.ic_star_full))
//                    character.favorited = 1
//                    itemClickFavorite(character.id ?: 0, 1)
//                }
//            }


        }
    }
}
