package com.camera3.android.application.di

import android.app.Application
import com.camera3.android.application.App
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import org.buffer.android.boilerplate.ui.injection.scopes.PerApplication

@PerApplication
@Component(modules = [
    ActivityBindingModule::class,
    ApplicationModule::class,
    AnalyticsModule::class,
    AndroidSupportInjectionModule::class])
interface ApplicationComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance fun application(application: Application): Builder
        fun build(): ApplicationComponent
    }

    fun inject(app: App)
}
