package com.camera3.android.core

interface StartupBean {
    fun init()
    fun optIn()
    fun optOut(): Boolean // should reboot
}