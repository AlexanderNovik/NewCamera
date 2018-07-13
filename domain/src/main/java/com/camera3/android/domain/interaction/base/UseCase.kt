package com.camera3.android.messenger.domain.base

import io.reactivex.Scheduler
import io.reactivex.disposables.Disposable
import io.reactivex.disposables.Disposables
import io.reactivex.observers.DisposableObserver
import timber.log.Timber

abstract class UseCase<ResultType> protected constructor(
        protected val scheduler: Scheduler,
        protected val postExecutionThread: Scheduler) {

    protected var disposable: Disposable = Disposables.empty()

    /**
     * Unsubscribe from current Subscription.
     */
    open fun unsubscribe() {
        if (!disposable.isDisposed) {
            disposable.dispose()
        }
    }

    class UseCaseSubscriber<ResultType>(private val next: (value: ResultType) -> Unit = {},
                                        private val error: (e: Throwable) -> Unit = { Timber.e(it.message) },
                                        private val complete: () -> Unit = {})
        : DisposableObserver<ResultType>() {
        override fun onError(e: Throwable) {
            error(e)
        }

        override fun onComplete() {
            complete()
        }

        override fun onNext(t: ResultType) {
            next(t)
        }
    }

}
