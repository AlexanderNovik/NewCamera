package com.camera3.android.analytic.amplitude

import android.app.Application
import android.os.Bundle
import com.amplitude.api.Amplitude
import com.amplitude.api.Identify
import com.camera3.android.analytic.toJson
import com.camera3.android.analytic.base.AnalyticsExternal
import javax.inject.Inject

interface AnalyticsAmplitude : AnalyticsExternal {

    class Impl @Inject constructor(
            private val application: Application,
            private val key: String) : AnalyticsAmplitude {

        private var initialized: Boolean = false

        override fun optIn() {
            initialize()
            Amplitude.getInstance().setOptOut(false)
        }

        override fun optOut(): Boolean {
            if (initialized) {
                Amplitude.getInstance().setOptOut(true)
            }
            return false
        }

        override fun logEvent(eventName: String, params: Bundle?) {
            params?.let {
                Amplitude.getInstance().logEvent(eventName, it.toJson())
            } ?: Amplitude.getInstance().logEvent(eventName)
        }

        override fun updateProperties(properties: Map<String, String>) {
            val identify = Identify()
            properties.forEach { identify.set(it.key, it.value) }
            Amplitude.getInstance().identify(identify)
        }

        private fun initialize() {
            if (!initialized) {
                Amplitude.getInstance()
                        .useAdvertisingIdForDeviceId()
                        .initialize(application, key)
                        .enableForegroundTracking(application)
                initialized = true
            }
        }
    }
}