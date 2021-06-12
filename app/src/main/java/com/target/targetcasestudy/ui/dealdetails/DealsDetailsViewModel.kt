package com.target.targetcasestudy.ui.dealdetails

import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.target.targetcasestudy.network.DealsSource
import com.target.targetcasestudy.network.data.DealsDetailsData
import kotlinx.coroutines.launch
import javax.inject.Inject

class DealsDetailsViewModel @Inject constructor(private val networkSource: DealsSource) :
    ViewModel() {

    var itemId = 0

    //LiveData for show Progress Bar
    private var _eventNetworkError = MutableLiveData(false)
    val eventNetworkError: LiveData<Boolean>
        get() = _eventNetworkError

    //LiveData of deals details
    private var _dealsDetails = MutableLiveData<DealsDetailsData>()
    val dealsDetails: LiveData<DealsDetailsData>
        get() = _dealsDetails

    fun errorClickListener() {
        fetchDealsLists(itemId)
    }

    fun setViewId(itemId: Int) {
        Log.d("ViewModel", "init view model")
        this.itemId = itemId
        fetchDealsLists(itemId)
    }


    private fun fetchDealsLists(itemId: Int) {
        Log.d("ViewModel", "load data")
        viewModelScope.launch{
            try {
                _dealsDetails.value = networkSource.fetchDealsDetails(itemId)
                _eventNetworkError.value = false
            } catch (e: Exception) {
                if (_dealsDetails.value == null) {
                    _eventNetworkError.value = true
                }
            }
        }
    }

}