package com.adclock.dto

import com.adclock.model.HandType
import com.adclock.model.Movement

data class HandUpdateRequest(
    val x: Int,
    val y: Int,
    val handType: HandType,
    val update: Movement
)
