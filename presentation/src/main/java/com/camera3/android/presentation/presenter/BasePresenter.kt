package com.camera3.android.presentation.presenter

import com.arellomobile.mvp.MvpPresenter
import com.arellomobile.mvp.MvpView

abstract class BasePresenter<T : MvpView> : MvpPresenter<T>()