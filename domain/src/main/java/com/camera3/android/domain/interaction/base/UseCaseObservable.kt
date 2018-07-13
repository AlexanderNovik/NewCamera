package com.camera3.android.messenger.domain.base

import io.reactivex.Observable

abstract class UseCaseObservable<in ParamType, ResultType> {
    abstract fun execute(param: ParamType): Observable<ResultType>
}