package com.adclock.model

data class Clock(val id: Int, val posX: Int, val posY: Int) {
    val hour = Hand()
    val minute = Hand()

    fun init() {
        TODO("Not yet implemented")
    }

    fun updateTarget() {
        hour.updateTarget()
        minute.updateTarget()
    }
}