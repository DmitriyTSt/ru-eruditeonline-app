package ru.eruditeonline.app.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import ru.eruditeonline.app.presentation.ui.auth.login.LoginFragment
import ru.eruditeonline.app.presentation.ui.auth.registration.RegistrationFragment
import ru.eruditeonline.app.presentation.ui.auth.registrationsuccess.RegistrationSuccessFragment
import ru.eruditeonline.app.presentation.ui.auth.validationsuccess.ValidationSuccessFragment
import ru.eruditeonline.app.presentation.ui.competition.detail.CompetitionDetailFragment
import ru.eruditeonline.app.presentation.ui.competition.filter.CompetitionFilterFragment
import ru.eruditeonline.app.presentation.ui.competition.items.CompetitionItemsFragment
import ru.eruditeonline.app.presentation.ui.country.SelectCountryFragment
import ru.eruditeonline.app.presentation.ui.dashboard.DashboardFragment
import ru.eruditeonline.app.presentation.ui.diploma.SelectDiplomaFragment
import ru.eruditeonline.app.presentation.ui.information.InformationFragment
import ru.eruditeonline.app.presentation.ui.profile.ProfileFragment
import ru.eruditeonline.app.presentation.ui.profile.anonym.AnonymProfileFragment
import ru.eruditeonline.app.presentation.ui.profile.user.UserProfileFragment
import ru.eruditeonline.app.presentation.ui.rating.RatingFragment
import ru.eruditeonline.app.presentation.ui.rating.tab.RatingTabItemFragment
import ru.eruditeonline.app.presentation.ui.result.common.CommonResultListFragment
import ru.eruditeonline.app.presentation.ui.result.search.SearchResultFragment
import ru.eruditeonline.app.presentation.ui.result.user.UserResultListFragment
import ru.eruditeonline.app.presentation.ui.splash.SplashFragment
import ru.eruditeonline.app.presentation.ui.test.passage.TestPassageFragment
import ru.eruditeonline.app.presentation.ui.test.tempresult.TestTempResultFragment

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

    @ContributesAndroidInjector
    abstract fun testTempResultFragment(): TestTempResultFragment

    @ContributesAndroidInjector
    abstract fun ratingTabItemFragment(): RatingTabItemFragment

    @ContributesAndroidInjector
    abstract fun commonResultListFragment(): CommonResultListFragment

    @ContributesAndroidInjector
    abstract fun searchResultFragment(): SearchResultFragment

    @ContributesAndroidInjector
    abstract fun userResultListFragment(): UserResultListFragment

    @ContributesAndroidInjector
    abstract fun loginFragment(): LoginFragment

    @ContributesAndroidInjector
    abstract fun anonymProfileFragment(): AnonymProfileFragment

    @ContributesAndroidInjector
    abstract fun userProfileFragment(): UserProfileFragment

    @ContributesAndroidInjector
    abstract fun selectCountryFragment(): SelectCountryFragment

    @ContributesAndroidInjector
    abstract fun selectDiplomaFragment(): SelectDiplomaFragment

    @ContributesAndroidInjector
    abstract fun registrationFragment(): RegistrationFragment

    @ContributesAndroidInjector
    abstract fun registrationSuccessFragment(): RegistrationSuccessFragment

    @ContributesAndroidInjector
    abstract fun validationSuccessFragment(): ValidationSuccessFragment

    @ContributesAndroidInjector
    abstract fun informationFragment(): InformationFragment
}
