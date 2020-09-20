package de.nicidienase.geniesser_app.ui.fccampus

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import de.nicidienase.geniesser_app.data.FcMeal
import de.nicidienase.geniesser_app.databinding.ItemFcDishBinding

class FcMealAdapter :
    ListAdapter<FcMeal, FcMealAdapter.FcMealViewHolder>(object : DiffUtil.ItemCallback<FcMeal>() {
        override fun areItemsTheSame(oldItem: FcMeal, newItem: FcMeal) = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: FcMeal, newItem: FcMeal) = oldItem == newItem
    }) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FcMealViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemFcDishBinding.inflate(inflater, parent, false)
        return FcMealViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FcMealViewHolder, position: Int) {
        holder.binding.dish = getItem(position)
    }

    class FcMealViewHolder(val binding: ItemFcDishBinding) : RecyclerView.ViewHolder(binding.root)
}
