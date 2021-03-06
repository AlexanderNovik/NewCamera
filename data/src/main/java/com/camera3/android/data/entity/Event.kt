package com.camera3.android.data.entity

import com.camera3.android.analytic.base.AnalyticsEvent

sealed class Event(private val name: String) : AnalyticsEvent {
    override fun getName(): String = name

    class AppStart : Event("APP_START")
}