package com.target.targetcasestudy.network

import com.target.targetcasestudy.network.data.DealsDetailsData
import com.target.targetcasestudy.network.data.DealsListData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DealsSource @Inject constructor(private val api: DealsAPI) {
    suspend fun fetchDealsList(): List<DealsListData.Product> = withContext(Dispatchers.IO) {
        api.getDealsList().products
    }

    suspend fun fetchDealsDetails(itemId: Int): DealsDetailsData = withContext(Dispatchers.IO) {
        api.getDealsDetails(itemId)
    }

}
