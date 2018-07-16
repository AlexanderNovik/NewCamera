package com.camera3.android.presentation

import com.camera3.android.domain.interaction.analytics.SendAppStartEvent
import com.camera3.android.presentation.main.MainPresenter
import io.reactivex.Scheduler
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Named

class MainPresenterImpl @Inject constructor(
        @Named("jobScheduler") private val jobScheduler: Scheduler,
        @Named("uiScheduler") private val uiScheduler: Scheduler,
        private val sendAppStartEvent: SendAppStartEvent) : MainPresenter {
    override fun init() {
    }

    override fun start() {
        sendAppStartEvent.execute(emptyMap())
                .subscribeOn(jobScheduler)
                .observeOn(uiScheduler)
                .subscribe({
                    Timber.d("Success")
                }, {
                    Timber.d("Error ${it.message}")
                })
    }

    override fun stop() {
    }

    override fun destroy() {
    }

}