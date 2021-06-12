package com.target.targetcasestudy.ui.dealslist

import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.target.targetcasestudy.network.DealsSource
import com.target.targetcasestudy.network.data.DealsListData
import kotlinx.coroutines.launch
import javax.inject.Inject

class DealsListViewModel @Inject constructor(private val networkSource: DealsSource) : ViewModel() {

    //LiveData for show Progress Bar
    private var _eventNetworkError = MutableLiveData(false)
    val eventNetworkError: LiveData<Boolean>
        get() = _eventNetworkError

    //LiveData of deals list
    private var _dealsList = MutableLiveData<List<DealsListData.Product>>()
    val dealsList: LiveData<List<DealsListData.Product>>
        get() = _dealsList

    fun errorClickListener() {
        fetchDealsLists()
    }

    init {
        Log.d("ViewModel", "init view model")
        fetchDealsLists()
    }


    private fun fetchDealsLists() {
        Log.d("ViewModel", "load data")
        viewModelScope.launch {
            try {
                _dealsList.value = networkSource.fetchDealsList()
                _eventNetworkError.value = false

            } catch (e: Exception) {
                if (dealsList.value.isNullOrEmpty()) {
                    _eventNetworkError.value = true
                }
            }
        }
    }
}