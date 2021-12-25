package com.inhouse.classifiedsapp.ui.fragment

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.ColorRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.inhouse.classifiedsapp.R
import com.inhouse.classifiedsapp.core.model.ClassifiedAd
import com.inhouse.classifiedsapp.databinding.FragmentHomeBinding
import com.inhouse.classifiedsapp.ui.adapter.ClassifiedsAdListAdapter
import com.inhouse.classifiedsapp.utils.ANIMATION_DURATION
import com.inhouse.classifiedsapp.utils.NetworkConnectionUtils
import com.inhouse.classifiedsapp.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private val homeViewModel: HomeViewModel by viewModels()
    private lateinit var binding: FragmentHomeBinding
    private lateinit var listAdapter: ClassifiedsAdListAdapter

    @Inject
    lateinit var networkConnectionUtils: NetworkConnectionUtils

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
                val classifiedAdUid = classifiedAd.uid
                findNavController().navigate(
                    HomeFragmentDirections.actionHomeToDetail(
                        classifiedAdUid
                    )
                )
            }
        })
        binding.lifecycleOwner = viewLifecycleOwner
        binding.homeViewModel = homeViewModel
        binding.listAdapter = listAdapter

        viewLifecycleOwner.lifecycleScope.launch {
            homeViewModel.classifiedsAdsListFlow.collect {
                listAdapter.submitList(it)
            }
        }
        observeNetworkConnectivityEvents()
    }

    private fun observeNetworkConnectivityEvents() {
        networkConnectionUtils.getNetworkLiveData().observe(viewLifecycleOwner) { isConnected ->
            if (!isConnected) {
                binding.tvNetworkStatus.text = getString(R.string.text_no_connectivity)
                binding.llNetworkStatus.apply {
                    visibility = View.VISIBLE
                    setBackgroundColor(getColorRes(R.color.network_error))
                }
            } else {
                binding.tvNetworkStatus.text = getString(R.string.text_connectivity)
                binding.llNetworkStatus.apply {
                    setBackgroundColor(getColorRes(R.color.network_connected))

                    animate()
                        .alpha(1.0f)
                        .setStartDelay(ANIMATION_DURATION)
                        .setDuration(ANIMATION_DURATION)
                        .setListener(object : AnimatorListenerAdapter() {
                            override fun onAnimationEnd(animation: Animator) {
                                visibility = View.GONE
                            }
                        })
                }
            }
        }
    }

    private fun getColorRes(@ColorRes id: Int): Int {
        return requireContext().getColor(id)
    }
}