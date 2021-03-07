package com.adclock.instructionset.instructions.wall

import com.adclock.instructionset.instructions.InstructionParser
import com.adclock.services.WallInteractionService

class RunInstruction : WallInstruction {

    override fun apply(wallService: WallInteractionService) {
        wallService.sendUpdate()
    }

    companion object : InstructionParser<RunInstruction> {
        override val key: String
            get() = "RUN"

        override fun deserialize(input: String): RunInstruction {
            require(input.isBlank()) { "No parameters for run instruction expected, but was $input." }
            return RunInstruction()
        }

        override fun serialize(instruction: RunInstruction) = ""
    }

}
