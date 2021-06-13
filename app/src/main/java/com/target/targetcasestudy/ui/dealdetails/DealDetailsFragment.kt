package com.target.targetcasestudy.ui.dealdetails

import android.graphics.Paint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.target.targetcasestudy.R
import com.target.targetcasestudy.dagger.App
import com.target.targetcasestudy.dagger.module.viewmodule.ViewModelFactory
import com.target.targetcasestudy.databinding.FragmentDealDetailsBinding
import com.target.targetcasestudy.utils.Constants
import javax.inject.Inject


class DealDetailsFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    lateinit var viewModel: DealsDetailsViewModel
    lateinit var binding: FragmentDealDetailsBinding

    private var errorSnackbar: Snackbar? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        (activity as AppCompatActivity?)?.supportActionBar?.hide()
        App.appComponent.inject(this)
        binding = FragmentDealDetailsBinding.inflate(inflater)
        viewModel = ViewModelProvider(this, viewModelFactory).get(DealsDetailsViewModel::class.java)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val itemId = DealDetailsFragmentArgs.fromBundle(requireArguments()).id
        viewModel.setViewId(itemId)

        //Strike the original price
        binding.dealsPrice.apply {
            paintFlags = paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
        }

        viewModel.eventNetworkError.observe(viewLifecycleOwner, Observer {
            if (it) {
                onNetworkError()
            }
        })

        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        super.onViewCreated(view, savedInstanceState)
    }

    //Function will show a toast when there is no internet
    private fun onNetworkError() {
        errorSnackbar = Snackbar.make(
            binding.root,
            getString(R.string.internet_error),
            Snackbar.LENGTH_INDEFINITE
        )
        errorSnackbar?.setActionTextColor(
            ContextCompat.getColor(
                requireContext(),
                R.color.white
            )
        )
        errorSnackbar?.setAction(R.string.retry) { viewModel.errorClickListener() }
        errorSnackbar?.show()
    }
}
