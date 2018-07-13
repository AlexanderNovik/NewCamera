package com.camera3.android.application.di

import android.app.Application
import com.camera3.android.analytic.amplitude.AmplitudeKeyProvider
import com.camera3.android.analytic.amplitude.AnalyticsAmplitude
import com.camera3.android.analytic.appsflyer.AnalyticsAppsFlyer
import com.camera3.android.analytic.appsflyer.AppsflyerKeyProvider
import com.camera3.android.analytic.base.AnalyticsManager
import com.camera3.android.analytic.facebook.AnalyticsFacebook
import dagger.Module
import dagger.Provides
import org.buffer.android.boilerplate.ui.injection.scopes.PerApplication

@Module
open class AnalyticsModule {
    @PerApplication
    @Provides
    fun provideAppsFlyerKey(): AppsflyerKeyProvider = object : AppsflyerKeyProvider {
        override fun getKey(): String = "AppsflyerKeyProvider"
    }

    @PerApplication
    @Provides
    fun provideAmplitudeKey(): AmplitudeKeyProvider = object : AmplitudeKeyProvider {
        override fun getKey(): String = "AmplitudeKeyProvider"
    }

    @PerApplication
    @Provides
    fun provideAnalyticsFacebook(app: Application): AnalyticsFacebook {
        return AnalyticsFacebook.Impl(app)
    }

    @PerApplication
    @Provides
    fun provideAnalyticsAppsFlyer(app: Application, key: AppsflyerKeyProvider): AnalyticsAppsFlyer {
        return AnalyticsAppsFlyer.Impl(app, key)
    }

    @PerApplication
    @Provides
    fun provideAnalyticsAmplitudeKey(app: Application, key: AmplitudeKeyProvider): AnalyticsAmplitude {
        return AnalyticsAmplitude.Impl(app, key)
    }


    @PerApplication
    @Provides
    fun provideAnalyticsManager(
            facebook: AnalyticsFacebook,
            amplitude: AnalyticsAmplitude,
            appsFlyer: AnalyticsAppsFlyer): AnalyticsManager {
        return AnalyticsManager.Impl(listOf(facebook, amplitude, appsFlyer))
    }
}