package de.nicidienase.geniesser_app.ui.preferences.qr

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import de.nicidienase.geniesser_app.api.GourmetApi
import de.nicidienase.geniesser_app.databinding.FragmentSelectLocationsBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class QrInfoFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentSelectLocationsBinding.inflate(inflater, container, false)

        val adapter = QrInfoAdapter()
        binding.locationList.adapter = adapter
        binding.locationList.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)

        lifecycleScope.launch(Dispatchers.IO) {
            val qrInfo = GourmetApi.instance.getQrInfo()
            withContext(Dispatchers.Main) {
                adapter.submitList(qrInfo.sortedBy { it.name })
            }
        }

        return binding.root
    }
}
