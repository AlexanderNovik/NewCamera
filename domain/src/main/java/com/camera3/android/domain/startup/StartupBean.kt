package com.camera3.android.domain.startup

interface StartupBean {
    fun init()
    fun optIn()
    fun optOut(): Boolean // should reboot
}