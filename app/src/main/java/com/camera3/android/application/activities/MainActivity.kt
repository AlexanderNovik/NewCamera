package com.camera3.android.application.activities

import android.os.Bundle
import com.camera3.android.application.R
import com.camera3.android.presentation.presenter.MainPresenter
import com.camera3.android.presentation.view.MainView
import dagger.android.AndroidInjection
import javax.inject.Inject

class MainActivity : BaseActivity(), MainView {
    override fun doSomethingForExample() {
    }

    @Inject lateinit var presenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        AndroidInjection.inject(this)
    }

    override fun onStart() {
        super.onStart()
        presenter.start()
    }
}
