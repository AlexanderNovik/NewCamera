package com.camera3.android.messenger.domain.base

import io.reactivex.Flowable

abstract class UseCaseFlowable<in ParamType, ResultType> {
    abstract fun execute(param: ParamType): Flowable<ResultType>
}