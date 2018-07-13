package com.camera3.android.analytic.appsflyer

import android.app.Application
import android.os.Bundle
import com.appsflyer.AppsFlyerLib
import com.camera3.android.analytic.toMapAny
import com.camera3.android.analytic.base.AnalyticsExternal
import javax.inject.Inject

interface AnalyticsAppsFlyer : AnalyticsExternal {

    class Impl @Inject constructor(private val application: Application,
                                   private val key: String) : AnalyticsAppsFlyer {
        private var initialized: Boolean = false

        override fun optIn() {
            initialize()
            AppsFlyerLib.getInstance().stopTracking(false, application)
        }

        override fun optOut(): Boolean {
            if (initialized) {
                AppsFlyerLib.getInstance().stopTracking(true, application)
            }
            return false
        }

        override fun logEvent(eventName: String, params: Bundle?) {
            AppsFlyerLib.getInstance().trackEvent(application, eventName, params?.toMapAny())
        }

        override fun updateProperties(properties: Map<String, String>) {
            AppsFlyerLib.getInstance().setAdditionalData(
                    HashMap(properties.mapValues { it.value as Any })
            )
        }

        private fun initialize() {
            if (!initialized) {
                AppsFlyerLib.getInstance().init(key, null, application)
                AppsFlyerLib.getInstance().startTracking(application)
                initialized = true
            }
        }
    }
}