package com.yuyu.sonic.home

import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.yuyu.sonic.Application
import com.yuyu.sonic.R
import com.yuyu.sonic.data.OrderState
import com.yuyu.sonic.databinding.FragmentHomeBinding
import com.yuyu.sonic.databinding.OrderBottomSheetBinding
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

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = HomeAdapter {
            findNavController().navigate(R.id.action_homeFragment_to_detailFragment, Bundle().apply {
                putParcelable("flight", it)
            })
        }
        binding.flightRecycler.adapter = adapter
        binding.flightRecycler.layoutManager = LinearLayoutManager(requireContext())
        binding.orderBottomSheet.lifecycleOwner = viewLifecycleOwner

        binding.filterLayout.setOnClickListener {
            binding.orderBottomSheet.root.visibility = View.VISIBLE
            binding.grayView.visibility = View.VISIBLE
        }

        binding.grayView.setOnClickListener {
            if(it.visibility == View.VISIBLE) {
                it.visibility = View.INVISIBLE
                binding.orderBottomSheet.root.visibility = View.GONE
            }
        }

        binding.orderBottomSheet.radioGroup.setOnCheckedChangeListener { radioGroup, i ->
            viewModel.updateState(when(i) {
                R.id.newest_radio -> OrderState.NEWEST
                else -> OrderState.OLDEST

            })
        }

        binding.topBtn.setOnClickListener {
            binding.flightRecycler.smoothScrollToPosition(0)
        }

        viewModel.checkState.observe(viewLifecycleOwner) {
            when(it) {
                OrderState.OLDEST -> {
                    binding.flightTv.text = resources.getString(R.string.oldest)
                    binding.flightTv.text = resources.getString(R.string.oldest)
                    adapter.submitList(viewModel.oldest)
                }
                OrderState.NEWEST -> {
                    binding.flightTv.text = resources.getString(R.string.newest)
                    binding.flightTv.text = resources.getString(R.string.newest)
                    adapter.submitList(viewModel.newest)
                }
            }

            binding.progress.visibility = View.GONE
            Handler().postDelayed( {
                binding.flightRecycler.scrollToPosition(0)
            }, 100)
            binding.grayView.visibility = View.INVISIBLE
            binding.orderBottomSheet.root.visibility = View.GONE
        }
    }
}