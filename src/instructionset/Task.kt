package com.adclock.instructionset

import com.adclock.instructionset.instructions.Instruction
import com.adclock.model.ClockWall

data class Task(val name: String, val instructions: MutableList<Instruction> = mutableListOf()) {
    private var current: Int = 0
    var sleepUntil: Long = 0
        internal set

    fun isCompleted() = current >= instructions.size

    fun apply(wall: ClockWall, preview: Boolean = false) {
        if (isCompleted())
            throw IllegalStateException("This task is already completed. Can't perform more steps.")

        if (sleepUntil > System.currentTimeMillis() && !preview)
            return

        if (applyInstruction(wall, current(), preview))
            current++
    }

    fun current() = instructions[current]

    internal fun restart() {
        current = 0
    }

    /**
     * Apply instruction to wall.
     * In normal Mode just execute once.
     * In preview Mode execute until a significant step was made.
     *
     * @param wall ClockWall
     * @param instruction Instruction
     * @param preview Boolean
     * @return Boolean
     */
    private fun applyInstruction(wall: ClockWall, instruction: Instruction, preview: Boolean): Boolean {
        var stepMade: Boolean
        do {
           stepMade = instruction.apply(this, wall)
        } while (preview && !stepMade)
        return stepMade
    }
}