package com.target.targetcasestudy.ui.dealslist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.target.targetcasestudy.R
import com.target.targetcasestudy.dagger.App
import com.target.targetcasestudy.dagger.module.viewmodule.ViewModelFactory
import com.target.targetcasestudy.databinding.FragmentDealListBinding
import com.target.targetcasestudy.network.data.DealsListData
import com.target.targetcasestudy.ui.DealItemAdapter
import com.target.targetcasestudy.ui.DealsListener
import com.target.targetcasestudy.utils.BaseFragment
import javax.inject.Inject

class DealListFragment : BaseFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    lateinit var viewModel: DealsListViewModel
    lateinit var binding: FragmentDealListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity as AppCompatActivity?)?.supportActionBar?.show()
        App.appComponent.inject(this)
        binding = FragmentDealListBinding.inflate(inflater)
        viewModel = ViewModelProvider(this, viewModelFactory).get(DealsListViewModel::class.java)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.dealsList.observe(viewLifecycleOwner, Observer {
            bindAdapter(it)
        })

        viewModel.eventNetworkError.observe(viewLifecycleOwner, Observer {
            if (it) {
                onNetworkError(binding.root, viewModel)
            }
        })

        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        super.onViewCreated(view, savedInstanceState)
    }

    private fun bindAdapter(it: List<DealsListData.Product>) {
        var adapter = DealItemAdapter(it, DealsListener {
            navigateToDetailsPage(it)
        })
        binding.recyclerView.adapter = adapter
    }

    private fun navigateToDetailsPage(it: DealsListData.Product) {
        findNavController().navigate(
            DealListFragmentDirections.actionOverviewFragmentToDetailFragment(
                it.id
            )
        )
    }
}
