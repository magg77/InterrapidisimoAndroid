package com.interrapidisimo.android.localities.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.interrapidisimo.android.databinding.FragmentLocalitiesBinding
import com.interrapidisimo.android.localities.presentation.LocalitiesViewModel

class LocalitiesFragment : Fragment() {

    private var _binding: FragmentLocalitiesBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val localitiesViewModel =
            ViewModelProvider(this).get(LocalitiesViewModel::class.java)

        _binding = FragmentLocalitiesBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textDashboard
        localitiesViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}