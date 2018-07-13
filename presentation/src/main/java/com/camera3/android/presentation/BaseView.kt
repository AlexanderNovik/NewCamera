package com.camera3.android.presentation

interface BaseView<in T : BasePresenter> {
    fun setPresenter(presenter: T)
}