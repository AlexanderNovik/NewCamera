package com.camera3.android.messenger.domain.base

import io.reactivex.Single

abstract class UseCaseSingle<in ParamType, ResultType> {
    abstract fun execute(param: ParamType): Single<ResultType>
}