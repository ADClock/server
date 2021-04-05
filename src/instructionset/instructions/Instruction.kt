package com.adclock.instructionset.instructions

import com.adclock.instructionset.Executor
import com.adclock.model.ClockWall

interface Instruction {
    fun apply(executor: Executor, wall: ClockWall): Boolean
}
