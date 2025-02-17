package com.interrapidisimo.android.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.interrapidisimo.android.core.utils.Constants
import com.interrapidisimo.android.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if(Constants.authenticateCustom != null){
            binding.tvIdentificacion.text = Constants.authenticateCustom.identificacion
            binding.tvNombre.text = Constants.authenticateCustom.nombre
            binding.tvUsuario.text = Constants.authenticateCustom.usuario
        }

        binding.btnTablas.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionNavigationHomeToNavigationTables())
        }

        binding.btnLocalidades.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionNavigationHomeToNavigationLocalities())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}