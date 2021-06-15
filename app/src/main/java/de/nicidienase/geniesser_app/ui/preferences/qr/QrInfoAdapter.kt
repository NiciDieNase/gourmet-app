package de.nicidienase.geniesser_app.ui.preferences.qr

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import de.nicidienase.geniesser_app.api.QrInfoDto
import de.nicidienase.geniesser_app.databinding.ItemQrInfoBinding

class QrInfoAdapter : ListAdapter<QrInfoDto, QrInfoAdapter.QrInfoViewHolder>(
    object : DiffUtil.ItemCallback<QrInfoDto>() {
        override fun areItemsTheSame(oldItem: QrInfoDto, newItem: QrInfoDto): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: QrInfoDto, newItem: QrInfoDto): Boolean =
            oldItem == newItem
    }
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QrInfoViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemQrInfoBinding.inflate(inflater, parent, false)
        return QrInfoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: QrInfoViewHolder, position: Int) {
        holder.binding.qrInfo = getItem(position)
    }

    class QrInfoViewHolder(val binding: ItemQrInfoBinding) : RecyclerView.ViewHolder(binding.root)
}
