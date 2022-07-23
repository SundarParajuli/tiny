package com.example.tinybean.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tinybean.R
import com.example.tinybean.databinding.FragmentMainBinding
import dagger.hilt.android.AndroidEntryPoint



@AndroidEntryPoint
class MainFragment : Fragment() {

    private val viewModel: MainViewModel by viewModels()
    private lateinit var binding: FragmentMainBinding
    val contentAdapter = ContentAdapter{
        navigateToDetailsFragment()
    }

    private fun navigateToDetailsFragment() {
        val action = MainFragmentDirections.actionMainFragmentToDetailFragment()
        findNavController().navigate(action)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setupToolBar()
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false)
        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            executePendingBindings()
            recycler.layoutManager = LinearLayoutManager(context)
            recycler.adapter = contentAdapter
        }

        viewModel.content.observe(viewLifecycleOwner) {
            contentAdapter.updateList(it)
        }
        viewModel.fetchContents()

        return binding.root
    }


    private fun setupToolBar() {
        (activity as AppCompatActivity).supportActionBar?.let {
            it.setHomeButtonEnabled(true)
            it.title = "TinyBean"
        }
    }

}