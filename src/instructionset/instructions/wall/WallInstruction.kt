package com.adclock.instructionset.instructions.wall

import com.adclock.instructionset.Task
import com.adclock.instructionset.instructions.Instruction
import com.adclock.services.WallInteractionService

interface WallInstruction : Instruction {
    fun apply(wallService: WallInteractionService)

    override fun apply(task: Task, wallService: WallInteractionService): Boolean {
        apply(wallService)
        return true // a wall instruction is always completed. It's a single instruction
    }
}
