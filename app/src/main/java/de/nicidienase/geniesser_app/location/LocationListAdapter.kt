package de.nicidienase.geniesser_app.location

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import de.nicidienase.geniesser_app.data.Location
import de.nicidienase.geniesser_app.databinding.ItemLocationBinding

class LocationListAdapter(val onClickAction: (Location) -> Unit) :
    ListAdapter<Location, LocationListAdapter.LocationViewHolder>(object : DiffUtil.ItemCallback<Location>() {
        override fun areItemsTheSame(oldItem: Location, newItem: Location): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Location, newItem: Location): Boolean {
            return oldItem == newItem
        }
    }) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationViewHolder {
        val binding = ItemLocationBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LocationViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LocationViewHolder, position: Int) {
        holder.binding.location = getItem(position)
        holder.binding.root.setOnClickListener {
            onClickAction(getItem(position))
        }
    }

    class LocationViewHolder(val binding: ItemLocationBinding) : RecyclerView.ViewHolder(binding.root)
}