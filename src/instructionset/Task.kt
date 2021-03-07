package com.adclock.instructionset

import com.adclock.instructionset.instructions.Instruction
import com.adclock.services.WallInteractionService

class Task(val instructions: List<Instruction>) {
    private var current: Int = 0
    var sleepUntil: Long = 0
        internal set

    fun isCompleted() = current >= instructions.size

    fun apply(wallService: WallInteractionService) {
        if (isCompleted())
            throw IllegalStateException("This task is already completed. Can't perform more steps.")

        if (sleepUntil > System.currentTimeMillis())
            return

        val instruction = instructions[current]
        if (instruction.apply(this, wallService))
            current++
    }

    internal fun restart() { current = 0 }
}