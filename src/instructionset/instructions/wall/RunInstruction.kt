package com.adclock.instructionset.instructions.wall

import com.adclock.instructionset.instructions.InstructionParser
import com.adclock.model.ClockWall
import com.adclock.services.WallInteractionService
import org.koin.core.component.KoinApiExtension
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class RunInstruction : WallInstruction {

    override fun apply(wall: ClockWall) {
        wallService.sendUpdate(wall)
    }

    @OptIn(KoinApiExtension::class)
    companion object : InstructionParser<RunInstruction>, KoinComponent {
        val wallService: WallInteractionService by inject()

        override val key: String
            get() = "RUN"

        override fun deserialize(input: String): RunInstruction {
            require(input.isBlank()) { "No parameters for run instruction expected, but was $input." }
            return RunInstruction()
        }

        override fun serialize(instruction: RunInstruction) = ""
    }

}
