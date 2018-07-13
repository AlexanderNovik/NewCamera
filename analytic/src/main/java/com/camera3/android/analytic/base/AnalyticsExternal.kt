package com.camera3.android.analytic.base

import android.os.Bundle

interface AnalyticsExternal {
    fun optIn()
    fun optOut(): Boolean

    fun logEvent(eventName: String, params: Bundle? = null)
    fun updateProperties(properties: Map<String, String>)
}