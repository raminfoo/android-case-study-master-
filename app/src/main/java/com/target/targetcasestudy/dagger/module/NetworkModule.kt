package com.target.targetcasestudy.dagger.module

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.target.targetcasestudy.network.DealsAPI
import com.target.targetcasestudy.utils.SharedPrefsHelper.PREF_FILE_NAME
import dagger.Module
import dagger.Provides
import dagger.Reusable
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetworkModule(private val application: Application) {

    @Provides
    @Reusable
    internal fun provideOkHttpClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BASIC

        val cacheDir = File(application.cacheDir, UUID.randomUUID().toString())
        // 15 MiB cache
        val cache = Cache(cacheDir, 15 * 1024 * 1024)
        return OkHttpClient.Builder()
            .cache(cache)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            //.addInterceptor(interceptor)
            .build()
    }

    @Provides
    @Reusable
    internal fun provideRetrofitInterface(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .baseUrl("https://api.target.com/mobile_case_study_deals/v1/")
        .client(okHttpClient)
        .build()

    @Provides
    @Reusable
    internal fun provideDealsListApi(retrofit: Retrofit): DealsAPI = retrofit.create(DealsAPI::class.java)

    @Provides
    @Singleton
    fun provideSharedPreference(): SharedPreferences {
        return application.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE)
    }
}