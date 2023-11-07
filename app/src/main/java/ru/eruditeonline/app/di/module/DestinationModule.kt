package ru.eruditeonline.app.di.module

import dagger.Module
import dagger.Provides
import ru.eruditeonline.app.presentation.composeui.dashboard.ComposeDashboardDestinationsImpl
import ru.eruditeonline.app.presentation.managers.ComposeFeatureManager
import ru.eruditeonline.app.presentation.ui.dashboard.DashboardDestinations
import ru.eruditeonline.app.presentation.ui.dashboard.DashboardDestinationsImpl

@Module
class DestinationModule {
    @Provides
    fun provideDashboardDestinations(
        composeFeatureManager: ComposeFeatureManager,
        dashboardDestinations: DashboardDestinationsImpl,
        composeDashboardDestinations: ComposeDashboardDestinationsImpl,
    ): DashboardDestinations {
        return if (composeFeatureManager.isComposeEnabled) {
            composeDashboardDestinations
        } else {
            dashboardDestinations
        }
    }
}