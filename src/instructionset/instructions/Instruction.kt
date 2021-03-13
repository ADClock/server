package com.adclock.instructionset.instructions

import com.adclock.instructionset.Task
import com.adclock.model.ClockWall

interface Instruction {
    fun apply(task: Task, wall: ClockWall): Boolean
}
