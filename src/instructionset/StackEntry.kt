package com.adclock.instructionset

import com.adclock.instructionset.instructions.Instruction

data class StackEntry(val task: Task, var current: Int = 0) {

    val completed: Boolean
        get() = task.instructions.size == current

    val currentInstruction: Instruction
        get() = task.instructions[current]
}