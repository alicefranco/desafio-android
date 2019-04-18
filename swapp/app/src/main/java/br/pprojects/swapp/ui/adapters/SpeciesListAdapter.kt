package br.pprojects.swapp.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import br.pprojects.swapp.R
import br.pprojects.swapp.models.Species
import kotlinx.android.synthetic.main.species_item.view.*

class SpeciesListAdapter(var context: Context, var itemClick: (id: Int) -> Unit) :
        ListAdapter<Species, SpeciesListAdapter.ViewHolder>(
                object: DiffUtil.ItemCallback<Species>(){
                    override fun areItemsTheSame(oldItem: Species, newItem: Species): Boolean = oldItem.id == newItem.id

                    override fun areContentsTheSame(oldItem: Species, newItem: Species): Boolean = oldItem == newItem
                }
        ) {

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.species_item, parent, false)
        return ViewHolder(view, context, itemClick)
    }

    override fun onBindViewHolder(holder: ViewHolder, pos: Int) {
        holder.bind(getItem(pos))
    }

    class ViewHolder(var item: View, var context: Context, var itemClick: (id: Int) -> Unit) : RecyclerView.ViewHolder(item)  {

        fun bind(species: Species) {
            item.tv_name.text = species.name
            item.tv_classification_value.text = species.classification
            item.tv_designation_value.text = species.designation
            item.tv_lifespan_value.text = species.averageLifespan.toString()
            item.tv_height_value.text = species.averageHeight
            item.tv_eye_color_value.text = species.eyeColors
            item.tv_hair_color_value.text = species.hairColors
            item.tv_skin_color_value.text = species.skinColors
            item.tv_language_value.text = species.language

//            item.cl_species.setOnClickListener {
//                //todo fragment list click
//                itemClick(species.id ?: 0)
//            }
        }
    }
}
