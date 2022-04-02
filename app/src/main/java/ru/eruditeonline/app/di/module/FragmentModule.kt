package ru.eruditeonline.app.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import ru.eruditeonline.app.presentation.ui.competition.filter.CompetitionFilterFragment
import ru.eruditeonline.app.presentation.ui.competition.items.CompetitionItemsFragment
import ru.eruditeonline.app.presentation.ui.dashboard.DashboardFragment
import ru.eruditeonline.app.presentation.ui.profile.ProfileFragment
import ru.eruditeonline.app.presentation.ui.rating.RatingFragment

@Module
abstract class FragmentModule {
    @ContributesAndroidInjector
    abstract fun dashboardFragment(): DashboardFragment

    @ContributesAndroidInjector
    abstract fun competitionItemsFragment(): CompetitionItemsFragment

    @ContributesAndroidInjector
    abstract fun ratingFragment(): RatingFragment

    @ContributesAndroidInjector
    abstract fun profileFragment(): ProfileFragment

    @ContributesAndroidInjector
    abstract fun competitionFilterFragment(): CompetitionFilterFragment
}
