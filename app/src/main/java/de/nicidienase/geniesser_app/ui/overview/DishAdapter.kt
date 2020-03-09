package de.nicidienase.geniesser_app.ui.overview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import de.nicidienase.geniesser_app.data.Dish
import de.nicidienase.geniesser_app.databinding.ItemDishBinding

class DishAdapter(val onItemSelected: (Dish, ItemDishBinding) -> Unit) : ListAdapter<Dish, DishAdapter.DishViewHolder>(object : DiffUtil.ItemCallback<Dish>() {
    override fun areItemsTheSame(oldItem: Dish, newItem: Dish) = oldItem.id == newItem.id
    override fun areContentsTheSame(oldItem: Dish, newItem: Dish) = oldItem == newItem
}) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DishViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemDishBinding.inflate(inflater, parent, false)
        return DishViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DishViewHolder, position: Int) {
        holder.binding.apply {
            dish = getItem(position)
            root.setOnClickListener {
                onItemSelected(getItem(position), this)
            }
        }
    }

    class DishViewHolder(val binding: ItemDishBinding) : RecyclerView.ViewHolder(binding.root)
}
