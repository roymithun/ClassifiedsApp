package com.inhouse.classifiedsapp.ui.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.inhouse.classifiedsapp.databinding.FragmentDetailBinding;
import com.inhouse.classifiedsapp.viewmodel.DetailViewModel;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class DetailFragment extends Fragment {

    private FragmentDetailBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentDetailBinding.inflate(inflater);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.setLifecycleOwner(getViewLifecycleOwner());
        String uid = DetailFragmentArgs.fromBundle(requireArguments()).getClassifiedUid();
        DetailViewModel detailViewModel = new ViewModelProvider(this).get(DetailViewModel.class);
        detailViewModel.getClassifiedsAdForUid(uid).observe(getViewLifecycleOwner(), item -> {
            binding.setClassifiedAd(item);
        });
    }
}
