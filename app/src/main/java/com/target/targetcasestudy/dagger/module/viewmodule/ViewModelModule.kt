package com.target.targetcasestudy.dagger.module.viewmodule

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.target.targetcasestudy.network.data.DealsDetailsData
import com.target.targetcasestudy.ui.dealdetails.DealsDetailsViewModel
import com.target.targetcasestudy.ui.dealslist.DealsListViewModel
import com.target.targetcasestudy.utils.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(DealsListViewModel::class)
    internal abstract fun bindOverviewViewModel(viewModel: DealsListViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DealsDetailsViewModel::class)
    internal abstract fun bindDetailViewModel(viewModel: DealsDetailsViewModel): ViewModel

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}