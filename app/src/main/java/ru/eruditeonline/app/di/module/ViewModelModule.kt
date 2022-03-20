package ru.eruditeonline.app.di.module

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import ru.eruditeonline.app.di.module.viewmodel.BaseViewModelModule
import ru.eruditeonline.app.di.util.ViewModelKey
import ru.eruditeonline.app.presentation.ui.competition.items.CompetitionItemsViewModel
import ru.eruditeonline.app.presentation.ui.dashboard.DashboardViewModel
import ru.eruditeonline.app.presentation.ui.profile.ProfileViewModel
import ru.eruditeonline.app.presentation.ui.rating.RatingViewModel

@Module
abstract class ViewModelModule : BaseViewModelModule() {
    @Binds
    @IntoMap
    @ViewModelKey(DashboardViewModel::class)
    abstract fun dashboardViewModel(viewModel: DashboardViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CompetitionItemsViewModel::class)
    abstract fun competitionItemsViewModel(viewModel: CompetitionItemsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(RatingViewModel::class)
    abstract fun ratingViewModel(viewModel: RatingViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ProfileViewModel::class)
    abstract fun profileViewModel(viewModel: ProfileViewModel): ViewModel
}
