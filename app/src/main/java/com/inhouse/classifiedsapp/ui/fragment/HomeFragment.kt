package com.inhouse.classifiedsapp.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.inhouse.classifiedsapp.core.model.ClassifiedAd
import com.inhouse.classifiedsapp.databinding.FragmentHomeBinding
import com.inhouse.classifiedsapp.ui.adapter.ClassifiedsAdListAdapter
import com.inhouse.classifiedsapp.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private val homeViewModel: HomeViewModel by viewModels()
    private lateinit var binding: FragmentHomeBinding
    private lateinit var listAdapter: ClassifiedsAdListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        listAdapter = ClassifiedsAdListAdapter(object : ClassifiedsAdListAdapter.OnClickListener {
            override fun onClick(classifiedAd: ClassifiedAd) {
                Toast.makeText(
                    requireContext(),
                    "Item Clicked with uid=${classifiedAd.uid}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
        binding.lifecycleOwner = viewLifecycleOwner
        binding.homeViewModel = homeViewModel
        binding.listAdapter = listAdapter
        homeViewModel.fetchClassifiedAds()
        homeViewModel.classifiedAdList.observe(viewLifecycleOwner) {
            it?.let {
                if (it.isEmpty()) {
                    homeViewModel.fetchClassifiedAds()
                } else {
                    listAdapter.submitList(it)
                }
            }
        }
    }
}