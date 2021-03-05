package com.adclock.model

enum class HandType(val short: Char) {
    HOUR('H'), MINUTE('M');

    companion object {
        val shortNames = values().map { it.short }
        fun valueOfShort(short: Char) = values().single { it.short == short }
    }
}