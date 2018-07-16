package com.camera3.android.application.di

import android.app.Application
import com.camera3.android.analytic.AnalyticsManagerImpl
import com.camera3.android.analytic.amplitude.AnalyticsAmplitude
import com.camera3.android.analytic.appsflyer.AnalyticsAppsFlyer
import com.camera3.android.analytic.base.AnalyticsManager
import com.camera3.android.analytic.facebook.AnalyticsFacebook
import com.camera3.android.data.repository.AnalyticsRepositoryImpl
import com.camera3.android.domain.repository.AnalyticsRepository
import dagger.Module
import dagger.Provides
import org.buffer.android.boilerplate.ui.injection.scopes.PerApplication

@Module
open class AnalyticsModule {

    @PerApplication
    @Provides
    fun provideAnalyticsFacebook(app: Application): AnalyticsFacebook {
        return AnalyticsFacebook.Impl(app)
    }

    @PerApplication
    @Provides
    fun provideAnalyticsAppsFlyer(app: Application): AnalyticsAppsFlyer {
        return AnalyticsAppsFlyer.Impl(app, "AppsflyerKey")
    }

    @PerApplication
    @Provides
    fun provideAnalyticsAmplitude(app: Application): AnalyticsAmplitude {
        return AnalyticsAmplitude.Impl(app, "AmplitudeKey")
    }


    @PerApplication
    @Provides
    fun provideAnalyticsManager(
            facebook: AnalyticsFacebook,
            amplitude: AnalyticsAmplitude,
            appsFlyer: AnalyticsAppsFlyer): AnalyticsManager {
        return AnalyticsManagerImpl(listOf(facebook, amplitude, appsFlyer))
    }

    @PerApplication
    @Provides
    fun provideAnalyticsRepository(analyticsManager: AnalyticsManager): AnalyticsRepository {
        return AnalyticsRepositoryImpl(analyticsManager)
    }
}