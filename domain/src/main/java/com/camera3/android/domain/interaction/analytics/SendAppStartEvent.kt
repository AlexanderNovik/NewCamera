package com.camera3.android.domain.interaction.analytics

import com.camera3.android.domain.repository.AnalyticsRepository
import com.camera3.android.messenger.domain.base.UseCaseSingle
import io.reactivex.Single
import javax.inject.Inject

class SendAppStartEvent
@Inject constructor(private val analyticsRepository: AnalyticsRepository)
    : UseCaseSingle<Map<String, String>, Unit>() {

    override fun execute(params: Map<String, String>): Single<Unit> =
            Single.fromCallable { analyticsRepository.sendStartAppEvent(params) }
}