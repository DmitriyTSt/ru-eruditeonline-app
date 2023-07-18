package ru.eruditeonline.app.di.module

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import ru.eruditeonline.app.di.module.viewmodel.BaseViewModelModule
import ru.eruditeonline.app.di.util.ViewModelKey
import ru.eruditeonline.app.presentation.ui.appupdate.AppUpdateViewModel
import ru.eruditeonline.app.presentation.ui.auth.login.LoginViewModel
import ru.eruditeonline.app.presentation.ui.auth.registration.RegistrationViewModel
import ru.eruditeonline.app.presentation.ui.auth.registrationsuccess.RegistrationSuccessViewModel
import ru.eruditeonline.app.presentation.ui.auth.validationsuccess.ValidationSuccessViewModel
import ru.eruditeonline.app.presentation.ui.competition.detail.CompetitionDetailViewModel
import ru.eruditeonline.app.presentation.ui.competition.filter.CompetitionFilterViewModel
import ru.eruditeonline.app.presentation.ui.competition.items.CompetitionItemsViewModel
import ru.eruditeonline.app.presentation.ui.country.SelectCountryViewModel
import ru.eruditeonline.app.presentation.ui.dashboard.DashboardViewModel
import ru.eruditeonline.app.presentation.ui.debug.DebugViewModel
import ru.eruditeonline.app.presentation.ui.diploma.SelectDiplomaViewModel
import ru.eruditeonline.app.presentation.ui.information.InformationViewModel
import ru.eruditeonline.app.presentation.ui.profile.ProfileViewModel
import ru.eruditeonline.app.presentation.ui.profile.anonym.AnonymProfileViewModel
import ru.eruditeonline.app.presentation.ui.profile.user.UserProfileViewModel
import ru.eruditeonline.app.presentation.ui.rating.RatingViewModel
import ru.eruditeonline.app.presentation.ui.rating.single.SingleTabRatingViewModel
import ru.eruditeonline.app.presentation.ui.rating.tab.RatingTabItemViewModel
import ru.eruditeonline.app.presentation.ui.result.common.CommonResultListViewModel
import ru.eruditeonline.app.presentation.ui.result.detail.ResultDetailViewModel
import ru.eruditeonline.app.presentation.ui.result.search.SearchResultViewModel
import ru.eruditeonline.app.presentation.ui.result.user.UserResultListViewModel
import ru.eruditeonline.app.presentation.ui.settings.SettingsViewModel
import ru.eruditeonline.app.presentation.ui.splash.SplashStartFlowViewModel
import ru.eruditeonline.app.presentation.ui.splash.SplashViewModel
import ru.eruditeonline.app.presentation.ui.test.passage.TestPassageViewModel
import ru.eruditeonline.app.presentation.ui.test.successresult.SuccessSaveResultViewModel
import ru.eruditeonline.app.presentation.ui.test.tempresult.TestTempResultViewModel
import ru.eruditeonline.app.presentation.ui.webpage.WebPageViewModel

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

    @Binds
    @IntoMap
    @ViewModelKey(CompetitionFilterViewModel::class)
    abstract fun competitionFilterViewModel(viewModel: CompetitionFilterViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SplashViewModel::class)
    abstract fun splashViewModel(viewModel: SplashViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CompetitionDetailViewModel::class)
    abstract fun competitionDetailViewModel(viewModel: CompetitionDetailViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(TestPassageViewModel::class)
    abstract fun testPassageViewModel(viewModel: TestPassageViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(TestTempResultViewModel::class)
    abstract fun testTempResultViewModel(viewModel: TestTempResultViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(RatingTabItemViewModel::class)
    abstract fun ratingTabItemViewModel(viewModel: RatingTabItemViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CommonResultListViewModel::class)
    abstract fun commonResultListViewModel(viewModel: CommonResultListViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SearchResultViewModel::class)
    abstract fun searchResultViewModel(viewModel: SearchResultViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(UserResultListViewModel::class)
    abstract fun userResultListViewModel(viewModel: UserResultListViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    abstract fun loginViewModel(viewModel: LoginViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(AnonymProfileViewModel::class)
    abstract fun anonymProfileViewModel(viewModel: AnonymProfileViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(UserProfileViewModel::class)
    abstract fun userProfileViewModel(viewModel: UserProfileViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SelectCountryViewModel::class)
    abstract fun selectCountryViewModel(viewModel: SelectCountryViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SelectDiplomaViewModel::class)
    abstract fun selectDiplomaViewModel(viewModel: SelectDiplomaViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(RegistrationViewModel::class)
    abstract fun registrationViewModel(viewModel: RegistrationViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(RegistrationSuccessViewModel::class)
    abstract fun registrationSuccessViewModel(viewModel: RegistrationSuccessViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ValidationSuccessViewModel::class)
    abstract fun validationSuccessViewModel(viewModel: ValidationSuccessViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(InformationViewModel::class)
    abstract fun informationViewModel(viewModel: InformationViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(WebPageViewModel::class)
    abstract fun webPageViewModel(viewModel: WebPageViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SuccessSaveResultViewModel::class)
    abstract fun successSaveResultViewModel(viewModel: SuccessSaveResultViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ResultDetailViewModel::class)
    abstract fun resultDetailViewModel(viewModel: ResultDetailViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SingleTabRatingViewModel::class)
    abstract fun singleTabRatingViewModel(viewModel: SingleTabRatingViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SettingsViewModel::class)
    abstract fun settingsViewModel(viewModel: SettingsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DebugViewModel::class)
    abstract fun debugViewModel(viewModel: DebugViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(AppUpdateViewModel::class)
    abstract fun appUpdateViewModel(viewModel: AppUpdateViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SplashStartFlowViewModel::class)
    abstract fun mainActivityViewModel(viewModel: SplashStartFlowViewModel): ViewModel
}
