package com.yuyu.sonic.detail

import android.animation.ValueAnimator
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.viewModels
import com.yuyu.sonic.R
import com.yuyu.sonic.data.FlightDataItem
import com.yuyu.sonic.databinding.FragmentDetailBinding
import com.yuyu.sonic.databinding.ItemCoresBinding


class DetailFragment : Fragment() {

    private lateinit var binding: FragmentDetailBinding
    private val viewModel by viewModels<DetailViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentDetailBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        binding.coresItem.lifecycleOwner = viewLifecycleOwner
        binding.coresItem.viewModel = viewModel


        arguments?.getParcelable<FlightDataItem>("flight")?.also {
            viewModel.getBundleData(it)
        }

        binding.coresLayout.setOnClickListener {
            val cores = binding.coresItem.coresLayout

            cores.apply {
                when (visibility) {
                    View.GONE -> {
                        setAnimation(this)
                        visibility = View.VISIBLE
                        binding.clickArrow.setImageResource(R.drawable.arrow_up)
                    }

                    View.VISIBLE -> {
                        visibility = View.GONE
                        binding.clickArrow.setImageResource(R.drawable.arrow_down)
                    }
                }
            }
        }

        binding.payloadsLayout.setOnClickListener {
            val item = binding.noInfo1

            item.apply {
                when(visibility) {
                    View.GONE -> {
                        setAnimation(this)
                        visibility = View.VISIBLE
                        binding.clickArrow2.setImageResource(R.drawable.arrow_up)
                    }

                    View.VISIBLE-> {
                        visibility = View.GONE
                        binding.clickArrow2.setImageResource(R.drawable.arrow_down)
                    }
                }
            }
        }

        binding.linksLayout.setOnClickListener {
            val item = binding.noInfo2

            item.apply {
                when(visibility) {
                    View.GONE -> {
                        setAnimation(this)
                        visibility = View.VISIBLE
                        binding.clickArrow3.setImageResource(R.drawable.arrow_up)
                    }

                    View.VISIBLE-> {
                        visibility = View.GONE
                        binding.clickArrow3.setImageResource(R.drawable.arrow_down)
                    }
                }
            }
        }

        return binding.root
    }

    private fun setAnimation(view: View) {
        view.apply {
            alpha = 0F

            val animator = ValueAnimator.ofFloat(0F, 1F)
            animator.setDuration(500L)
            animator.addUpdateListener { animator->
                alpha = animator.animatedValue as Float
            }
            animator.start()
        }
    }
}