package com.camera3.android.application.di

import com.camera3.android.domain.interaction.analytics.SendAppStartEvent
import com.camera3.android.presentation.MainPresenterImpl
import com.camera3.android.presentation.main.MainPresenter
import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import org.buffer.android.boilerplate.ui.injection.scopes.PerActivity
import javax.inject.Named

@Module
open class MainActivityModule {
    @PerActivity
    @Provides
    internal fun provideMainPresenter(
            sendAppStartEvent: SendAppStartEvent,
            @Named("jobScheduler") jobScheduler: Scheduler,
            @Named("uiScheduler") uiScheduler: Scheduler
    ): MainPresenter = MainPresenterImpl(jobScheduler, uiScheduler, sendAppStartEvent)

}