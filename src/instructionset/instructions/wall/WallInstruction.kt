package com.adclock.instructionset.instructions.wall

import com.adclock.instructionset.Task
import com.adclock.instructionset.instructions.Instruction
import com.adclock.model.ClockWall

interface WallInstruction : Instruction {
    fun apply(wall: ClockWall)

    override fun apply(task: Task, wall: ClockWall): Boolean {
        apply(wall)
        return true // a wall instruction is always completed. It's a single instruction
    }
}
