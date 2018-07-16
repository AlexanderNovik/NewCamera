package com.camera3.android.analytic.base

interface AnalyticsManager {
    fun logEvent(event: AnalyticsEvent, params: Map<String, String>)
    fun init()
    fun optIn()
    fun optOut(): Boolean
}