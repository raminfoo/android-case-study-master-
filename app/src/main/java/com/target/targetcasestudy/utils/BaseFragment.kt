package com.target.targetcasestudy.utils

import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import com.google.android.material.snackbar.Snackbar
import com.target.targetcasestudy.R
import com.target.targetcasestudy.ui.dealdetails.DealsDetailsViewModel
import com.target.targetcasestudy.ui.dealslist.DealsListViewModel

open class BaseFragment : Fragment() {

    private var errorSnackbar: Snackbar? = null

    //Function will show a toast when there is no internet
    fun onNetworkError(view: View, viewModel: ViewModel) {
        errorSnackbar = Snackbar.make(
            view,
            getString(R.string.internet_error),
            Snackbar.LENGTH_INDEFINITE
        )
        errorSnackbar?.setActionTextColor(
            ContextCompat.getColor(
                requireContext(),
                R.color.white
            )
        )

        errorSnackbar?.setAction(R.string.retry) {
            if(viewModel is DealsListViewModel){
                viewModel.errorClickListener()
            } else  if(viewModel is DealsDetailsViewModel) {
                viewModel.errorClickListener()
            }
        }
        errorSnackbar?.show()
    }
}