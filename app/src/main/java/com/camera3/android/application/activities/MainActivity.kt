package com.camera3.android.application.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.camera3.android.application.R
import com.camera3.android.presentation.main.MainPresenter
import dagger.android.AndroidInjection
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

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

    override fun onDestroy() {
        super.onDestroy()
    }
}
