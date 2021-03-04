package com.adclock.model

data class Clock(val posX: Int, val posY: Int) {
    val hour = Hand()
    val minute = Hand()
}