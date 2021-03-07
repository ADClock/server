package com.adclock.model

data class Clock(val id: Int, val posX: Int, val posY: Int) {
    val hour = Hand()
    val minute = Hand()

    fun init() {
        hour.init()
        minute.init()
    }

    fun updateTarget() {
        hour.updateTarget()
        minute.updateTarget()
    }
}