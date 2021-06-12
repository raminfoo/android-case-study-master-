package com.target.targetcasestudy.dagger.component
import com.target.targetcasestudy.dagger.module.NetworkModule
import com.target.targetcasestudy.dagger.module.viewmodule.ViewModelModule
import com.target.targetcasestudy.ui.dealdetails.DealDetailsFragment
import com.target.targetcasestudy.ui.dealslist.DealListFragment
import com.target.targetcasestudy.ui.payment.PaymentDialogFragment
import com.target.targetcasestudy.utils.SharedPrefsHelper


import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class, ViewModelModule::class])
interface AppComponent{
    fun getSharedPrefsHelper(): SharedPrefsHelper?

    fun inject(dealListFragment: DealListFragment)
    fun inject(detailFragment: DealDetailsFragment)
    fun inject(dialogFragment: PaymentDialogFragment)
}