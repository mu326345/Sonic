package com.yuyu.sonic.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.yuyu.sonic.Application
import com.yuyu.sonic.R
import com.yuyu.sonic.databinding.FragmentHomeBinding
import com.yuyu.sonic.factory.ViewModelFactory


class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var adapter: HomeAdapter
    private val viewModel by viewModels<HomeViewModel> {
        ViewModelFactory((context?.applicationContext as Application).repository)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner

        adapter = HomeAdapter {
            findNavController().navigate(R.id.action_homeFragment_to_detailFragment, Bundle().apply {
                putParcelable("flight", it)
            })
        }
        binding.flightRecycler.adapter = adapter
        binding.flightRecycler.layoutManager = LinearLayoutManager(requireContext())

        viewModel.flightItem.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }

        return binding.root
    }
}