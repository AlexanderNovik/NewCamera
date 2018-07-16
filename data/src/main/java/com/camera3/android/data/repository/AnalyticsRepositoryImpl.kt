package com.camera3.android.data.repository

import com.camera3.android.analytic.base.AnalyticsManager
import com.camera3.android.data.entity.Event
import com.camera3.android.domain.repository.AnalyticsRepository
import javax.inject.Inject

class AnalyticsRepositoryImpl @Inject constructor(
        private val analyticsManager: AnalyticsManager) : AnalyticsRepository {

    override fun sendStartAppEvent(params: Map<String, String>) {
        analyticsManager.logEvent(Event.AppStart(), params)
    }
}