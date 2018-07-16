package com.camera3.android.domain.repository

interface AnalyticsRepository {
    fun sendStartAppEvent(params: Map<String, String>)
}