package com.camera3.android.presentation.executor

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import org.buffer.android.boilerplate.domain.executor.PostExecutionThread
import javax.inject.Inject

class UiThread @Inject internal constructor() : PostExecutionThread {
    override val scheduler: Scheduler
        get() = AndroidSchedulers.mainThread()

}