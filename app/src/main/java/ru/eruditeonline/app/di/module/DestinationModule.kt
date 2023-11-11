package ru.eruditeonline.app.di.module

import dagger.Module
import dagger.Provides
import ru.eruditeonline.app.presentation.composeui.competition.items.ComposeCompetitionItemsDestinationsImpl
import ru.eruditeonline.app.presentation.composeui.dashboard.ComposeDashboardDestinationsImpl
import ru.eruditeonline.app.presentation.composeui.profile.ComposeAnonymProfileDestinationsImpl
import ru.eruditeonline.app.presentation.composeui.profile.ComposeCommonProfileDestinationsImpl
import ru.eruditeonline.app.presentation.managers.ComposeFeatureManager
import ru.eruditeonline.app.presentation.ui.competition.items.CompetitionItemsDestinations
import ru.eruditeonline.app.presentation.ui.competition.items.CompetitionItemsDestinationsImpl
import ru.eruditeonline.app.presentation.ui.dashboard.DashboardDestinations
import ru.eruditeonline.app.presentation.ui.dashboard.DashboardDestinationsImpl
import ru.eruditeonline.app.presentation.ui.profile.CommonProfileDestinations
import ru.eruditeonline.app.presentation.ui.profile.CommonProfileDestinationsImpl
import ru.eruditeonline.app.presentation.ui.profile.anonym.AnonymProfileDestinations
import ru.eruditeonline.app.presentation.ui.profile.anonym.AnonymProfileDestinationsImpl

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

    @Provides
    fun provideCompetitionItemsDestinations(
        composeFeatureManager: ComposeFeatureManager,
        competitionItemsDestinations: CompetitionItemsDestinationsImpl,
        composeCompetitionItemsDestinations: ComposeCompetitionItemsDestinationsImpl,
    ): CompetitionItemsDestinations {
        return if (composeFeatureManager.isComposeEnabled) {
            composeCompetitionItemsDestinations
        } else {
            competitionItemsDestinations
        }
    }

    @Provides
    fun provideCommonProfileDestinations(
        composeFeatureManager: ComposeFeatureManager,
        commonProfileDestinations: CommonProfileDestinationsImpl,
        composeCommonProfileDestinations: ComposeCommonProfileDestinationsImpl,
    ): CommonProfileDestinations {
        return if (composeFeatureManager.isComposeEnabled) {
            composeCommonProfileDestinations
        } else {
            commonProfileDestinations
        }
    }

    @Provides
    fun provideAnonymProfileDestinations(
        composeFeatureManager: ComposeFeatureManager,
        anonymProfileDestinations: AnonymProfileDestinationsImpl,
        composeAnonymProfileDestinations: ComposeAnonymProfileDestinationsImpl,
    ): AnonymProfileDestinations {
        return if (composeFeatureManager.isComposeEnabled) {
            composeAnonymProfileDestinations
        } else {
            anonymProfileDestinations
        }
    }
}
