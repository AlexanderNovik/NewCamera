package com.camera3.android.presentation.main

import com.camera3.android.domain.interaction.analytics.SendAppStartEvent
import com.camera3.android.presentation.BasePresenter
import io.reactivex.Scheduler
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Named

interface MainPresenter : BasePresenter