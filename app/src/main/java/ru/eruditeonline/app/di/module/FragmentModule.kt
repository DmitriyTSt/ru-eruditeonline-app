package ru.eruditeonline.app.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import ru.eruditeonline.app.presentation.ui.competition.detail.CompetitionDetailFragment
import ru.eruditeonline.app.presentation.ui.competition.filter.CompetitionFilterFragment
import ru.eruditeonline.app.presentation.ui.competition.items.CompetitionItemsFragment
import ru.eruditeonline.app.presentation.ui.dashboard.DashboardFragment
import ru.eruditeonline.app.presentation.ui.profile.ProfileFragment
import ru.eruditeonline.app.presentation.ui.rating.RatingFragment
import ru.eruditeonline.app.presentation.ui.splash.SplashFragment
import ru.eruditeonline.app.presentation.ui.test.passage.TestPassageFragment

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

    @ContributesAndroidInjector
    abstract fun splashFragment(): SplashFragment

    @ContributesAndroidInjector
    abstract fun competitionDetailFragment(): CompetitionDetailFragment

    @ContributesAndroidInjector
    abstract fun testPassageFragment(): TestPassageFragment
}
