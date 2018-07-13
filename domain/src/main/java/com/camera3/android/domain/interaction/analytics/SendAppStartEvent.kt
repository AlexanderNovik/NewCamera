package com.camera3.android.domain.interaction.analytics

import com.camera3.android.analytic.base.AnalyticsManager
import com.camera3.android.messenger.domain.base.UseCaseSingle
import io.reactivex.Single
import javax.inject.Inject

class SendAppStartEvent
@Inject constructor(private val analyticsManager: AnalyticsManager)
    : UseCaseSingle<Map<String, String>, Unit>() {

    override fun execute(params: Map<String, String>): Single<Unit> =
            Single.fromCallable { analyticsManager.logEvent(Event.AppStart(), params) }
}