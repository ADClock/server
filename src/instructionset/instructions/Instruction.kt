package com.adclock.instructionset.instructions

import com.adclock.instructionset.Task
import com.adclock.services.WallInteractionService

interface Instruction {
    fun apply(task: Task, wallService: WallInteractionService): Boolean
}
