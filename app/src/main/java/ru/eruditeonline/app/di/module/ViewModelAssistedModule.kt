package ru.eruditeonline.app.di.module

import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import ru.eruditeonline.app.di.util.AbstractAssistedViewModelFactory
import ru.eruditeonline.app.di.util.ViewModelFactoryKey
import ru.eruditeonline.app.presentation.ui.dashboard.DashboardViewModel

@Module
abstract class ViewModelAssistedModule {

    @Binds
    @IntoMap
    @ViewModelFactoryKey(DashboardViewModel::class)
    abstract fun dashboardViewModel(viewModel: DashboardViewModel.Factory): AbstractAssistedViewModelFactory<DashboardViewModel>

}