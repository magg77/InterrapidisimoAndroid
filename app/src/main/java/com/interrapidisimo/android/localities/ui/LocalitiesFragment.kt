package com.interrapidisimo.android.localities.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import com.interrapidisimo.android.core.valueObjet.AdapterLocalities
import com.interrapidisimo.android.core.valueObjet.ResourceState
import com.interrapidisimo.android.databinding.FragmentLocalitiesBinding
import com.interrapidisimo.android.localities.presentation.LocalitiesViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LocalitiesFragment : Fragment() {

    private var _binding: FragmentLocalitiesBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val localitiesViewModel by viewModels<LocalitiesViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentLocalitiesBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupLayoutsMaterialToolbar()
        setupObservers()
        setupLayoutsAdapter()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    //custom methods *******************************************************************************

    private fun setupLayoutsMaterialToolbar() {

        // this is compulsory in order to get behavior of expand/collapse
        (requireActivity() as AppCompatActivity).setSupportActionBar(binding.materialToolbar)
    }

    private fun setupLayoutsAdapter() {

        val layoutManager = GridLayoutManager(requireContext(), 1)
        /*layoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                val adapter = binding.rvHomeFragment.adapter as? AdapterLocalities
                return if (adapter?.getItemViewType(position) == AdapterLocalities.VIEW_TYPE_HEADER) 2 else 1
            }
        }*/
        binding.rvHomeFragment.setHasFixedSize(true)
        binding.rvHomeFragment.isNestedScrollingEnabled = false // Deshabilita el scrolling del RecyclerView dentro del NestedScrollView
        binding.rvHomeFragment.overScrollMode = View.OVER_SCROLL_NEVER
        binding.rvHomeFragment.layoutManager = layoutManager
    }

    private fun setupObservers() {

        localitiesViewModel.getLocalitiesViewModel()

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED){
                localitiesViewModel.uiStateLocalities.collect(){

                    when(it){

                        is ResourceState.LoadingState -> {
                            binding.psHome.visibility = View.VISIBLE
                        }

                        is ResourceState.SuccessState -> {
                            binding.psHome.visibility = View.GONE

                            binding.rvHomeFragment.adapter =
                                AdapterLocalities(
                                    context = requireContext(),
                                    localitiesResponseItem = listOf("Localidades") + it.data,
                                    onItemClickListener = {

                                    }
                                )
                        }

                        is ResourceState.FailureState -> {
                            binding.psHome.visibility = View.GONE
                            Toast.makeText(
                                requireContext(),
                                "Ocurrio un error al mostrar los datos: ${it.message}",
                                Toast.LENGTH_SHORT
                            ).show()
                        }

                        else -> {
                            Log.i("localities", "Else when")
                        }



                    }

                }
            }
        }


    }



}