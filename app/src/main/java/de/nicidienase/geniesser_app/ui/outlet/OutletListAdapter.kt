package de.nicidienase.geniesser_app.ui.outlet

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import de.nicidienase.geniesser_app.data.Outlet
import de.nicidienase.geniesser_app.databinding.ItemOutletBinding

class OutletListAdapter(val onClickAction: (Outlet) -> Unit) :
    ListAdapter<Outlet, OutletListAdapter.OutletViewHolder>(
        object : DiffUtil.ItemCallback<Outlet>() {
            override fun areItemsTheSame(oldItem: Outlet, newItem: Outlet) =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Outlet, newItem: Outlet) = oldItem == newItem
        }) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OutletViewHolder {
        val binding = ItemOutletBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return OutletViewHolder(binding)
    }

    override fun onBindViewHolder(holder: OutletViewHolder, position: Int) {
        val item = getItem(position)
        holder.binding.outlet = item
        holder.binding.root.setOnClickListener {
            onClickAction(item)
        }
    }

    inner class OutletViewHolder(val binding: ItemOutletBinding) :
        RecyclerView.ViewHolder(binding.root)
}
