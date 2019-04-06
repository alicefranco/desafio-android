package br.pprojects.swapp.ui.adapters

import android.content.Context
import android.support.v7.recyclerview.extensions.ListAdapter
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.pprojects.swapp.R
import br.pprojects.swapp.models.Planet
import kotlinx.android.synthetic.main.planet_item.view.*

class PlanetsListAdapter(var context: Context, var itemClick: (id: Int) -> Unit) :
        ListAdapter<Planet, PlanetsListAdapter.ViewHolder>(
                object: DiffUtil.ItemCallback<Planet>(){
                    override fun areItemsTheSame(oldItem: Planet, newItem: Planet): Boolean = oldItem.id == newItem.id

                    override fun areContentsTheSame(oldItem: Planet, newItem: Planet): Boolean = oldItem == newItem
                }
        ) {

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.planet_item, parent, false)
        return ViewHolder(view, context, itemClick)
    }

    override fun onBindViewHolder(holder: ViewHolder, pos: Int) {
        holder.bind(getItem(pos))
    }

    class ViewHolder(var item: View, var context: Context, var itemClick: (id: Int) -> Unit) : RecyclerView.ViewHolder(item)  {

        fun bind(planet: Planet) {
            item.tv_name.text = planet.name
            item.tv_climate_value.text = planet.climate
            item.tv_terrain_value.text = planet.terrain
            item.tv_population_value.text = planet.population
            item.tv_water_value.text = planet.surfaceWater
            item.tv_gravity_value.text = planet.gravity

//            item.cl_planet.setOnClickListener {
//                //todo fragment list click
//                itemClick(planet.id ?: 0)
//            }
        }
    }
}
