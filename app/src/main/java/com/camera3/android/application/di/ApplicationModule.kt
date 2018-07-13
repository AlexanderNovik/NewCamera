package com.camera3.android.application.di

import android.app.Application
import android.content.Context
import com.camera3.android.presentation.executor.JobExecutor
import com.camera3.android.presentation.executor.UiThread
import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers
import org.buffer.android.boilerplate.domain.executor.PostExecutionThread
import org.buffer.android.boilerplate.domain.executor.ThreadExecutor
import org.buffer.android.boilerplate.ui.injection.scopes.PerApplication
import javax.inject.Named

@Module
open class ApplicationModule {
    @Provides
    @PerApplication
    fun provideContext(application: Application): Context {
        return application
    }

    @Provides
    @PerApplication
    internal fun provideThreadExecutor(jobExecutor: JobExecutor): ThreadExecutor {
        return jobExecutor
    }

    @Provides
    @PerApplication
    internal fun providePostExecutionThread(uiThread: UiThread): PostExecutionThread {
        return uiThread
    }

    @Provides
    @PerApplication
    @Named("jobScheduler")
    fun provideThreadScheduler(thread: ThreadExecutor): Scheduler {
        return Schedulers.from(thread)
    }

    @Provides
    @PerApplication
    @Named("uiScheduler")
    fun providePostExecutionScheduler(thread: PostExecutionThread): Scheduler {
        return thread.scheduler
    }
}