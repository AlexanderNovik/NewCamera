package com.camera3.android.analytic.base

import android.os.Bundle
import com.camera3.android.core.StartupBean

interface AnalyticsManager : StartupBean {
    fun logEvent(event: AnalyticsEvent, params: Map<String, String>)

    class Impl(private val analytics: List<AnalyticsExternal>) : AnalyticsManager {

        private lateinit var analyticsFacade: AnalyticsFacade

        override fun init() {
            analyticsFacade = AnalyticsFacade().apply {
                analytics.forEach { addExternalAnalytics(it) }
            }
        }

        override fun optIn() {
            if (!::analyticsFacade.isInitialized) {
                init()
            }
            analyticsFacade.optIn()
        }

        override fun optOut(): Boolean {
            return analyticsFacade.optOut()
        }

        override fun logEvent(event: AnalyticsEvent, params: Map<String, String>) {
            analyticsFacade.logEvent(event, transformParams(params))
        }

        private fun transformParams(params: Map<String, String>): Bundle {
            return Bundle().apply {
                params.forEach {
                    putString(it.key, it.value)
                }
            }
        }

        private class AnalyticsFacade {

            private lateinit var isOptOut: () -> Boolean
            private val analytics = mutableListOf<AnalyticsExternal>()

            fun setIsOptOut(isOptOut: () -> Boolean) {
                this.isOptOut = isOptOut
                if (isOptOut()) {
                    optOut()
                } else {
                    optIn()
                }
            }

            fun addExternalAnalytics(analyticsExternal: AnalyticsExternal) {
                analytics.add(analyticsExternal)
            }

            fun optIn() {
                analytics.forEach {
                    it.optIn()
                }
            }

            fun optOut(): Boolean {
                return analytics.fold(false) { acc, ext ->
                    ext.optOut() or acc
                }
            }

            // do not perform logging when camera visible (because of GC)
            fun logEvent(event: AnalyticsEvent, params: Bundle?) {
                if (!isOptOut()) {
                    analytics.forEach {
                        it.logEvent(event.getName(), params)
                    }
                }
            }

            fun updateProperties(properties: Map<String, String>) {
                if (!isOptOut()) {
                    analytics.forEach {
                        it.updateProperties(properties)
                    }
                }
            }
        }
    }
}