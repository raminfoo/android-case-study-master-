package com.target.targetcasestudy.network

import com.target.targetcasestudy.network.data.DealsDetailsData
import com.target.targetcasestudy.network.data.DealsListData
import retrofit2.http.GET
import retrofit2.http.Path

//retrofit service to fetch deals data
interface DealsAPI {
    @GET("deals")
    suspend fun getDealsList(): DealsListData

    @GET("deals/{dealsId}")
    suspend fun getDealsDetails(@Path("dealsId") dealsId: Int): DealsDetailsData
}