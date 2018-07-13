package com.camera3.android.analytic.facebook

import android.app.Application
import android.os.Bundle
import com.camera3.android.analytic.base.AnalyticsExternal
import com.facebook.appevents.AppEventsLogger
import javax.inject.Inject

interface AnalyticsFacebook : AnalyticsExternal {

    class Impl @Inject constructor(private val application: Application) : AnalyticsFacebook {
        private var appEventsLogger: AppEventsLogger? = null

        override fun optIn() {
            AppEventsLogger.activateApp(application)
            appEventsLogger = AppEventsLogger.newLogger(application)
        }

        override fun optOut(): Boolean {
            // for deactivation app should be restarted
            appEventsLogger = null
            return true
        }

        override fun logEvent(eventName: String, params: Bundle?) {
            appEventsLogger?.logEvent(eventName, params)
        }

        override fun updateProperties(properties: Map<String, String>) {
            AppEventsLogger.updateUserProperties(
                    Bundle().apply {
                        properties.forEach { (k, v) ->
                            this.putString(k, v)
                        }
                    },
                    null)
        }
    }
}