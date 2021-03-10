package com.adclock.instructionset

import com.adclock.instructionset.instructions.Instruction
import com.adclock.model.ClockWall
import com.adclock.services.WallInteractionService

data class Task(val name: String, val instructions: List<Instruction>) {
    private var current: Int = 0
    var sleepUntil: Long = 0
        internal set

    fun isCompleted() = current >= instructions.size

    fun apply(wall: ClockWall) {
        if (isCompleted())
            throw IllegalStateException("This task is already completed. Can't perform more steps.")

        if (sleepUntil > System.currentTimeMillis())
            return

        val instruction = instructions[current]
        if (instruction.apply(this, wall))
            current++
    }

    internal fun restart() { current = 0 }
}