package com.example.tinybean.ui.main.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.addCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.tinybean.R
import com.example.tinybean.data.Result
import com.example.tinybean.databinding.FragmentDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment() {

    private val viewModel: DetailViewModel by viewModels()
    private lateinit var binding: FragmentDetailBinding

    private val detailAdapter = DetailAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireActivity().onBackPressedDispatcher.addCallback(this) {
            findNavController().navigateUp()
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setupToolBar()
        setupDataBinding(inflater, container)
        setupRecyclerView()
        return binding.root
    }

    private fun setupDataBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail, container, false)
        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            executePendingBindings()
        }
    }

    private fun setupToolBar() {
        (activity as AppCompatActivity).supportActionBar?.let {
            it.setHomeButtonEnabled(true)
            it.title = "TinyBean details"
        }
    }

    private fun setupRecyclerView() {
        with(binding.rvImageList) {
            val gridLayoutManager = GridLayoutManager(requireContext(),2)
            gridLayoutManager.orientation = GridLayoutManager.VERTICAL
            layoutManager = gridLayoutManager

            adapter = detailAdapter
        }

        binding.srImageList.setOnRefreshListener {
            viewModel.fetchList()
        }

        viewModel.imagesLiveData.observe(viewLifecycleOwner) {
           when (it) {
                is Result.Success -> {
                    detailAdapter.updateList(it.data?.images ?: listOf())
                    binding.srImageList.isRefreshing = false
                }
                is Result.Error -> {
                    binding.srImageList.isRefreshing = false
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                }
                is Result.Loading -> {
                    binding.srImageList.isRefreshing = true
                }

           }
        }
    }


}