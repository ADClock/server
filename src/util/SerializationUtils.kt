package com.adclock.util

import com.adclock.model.Clock
import com.adclock.model.Hand

fun Clock.serialize() = hour.serialize() + minute.serialize()

fun Hand.serialize(): ByteArray {
    return ByteArray(4)
}