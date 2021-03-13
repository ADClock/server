package com.adclock.instructionset.instructions.hand

import com.adclock.instructionset.clockselections.ClockSelection
import com.adclock.instructionset.instructions.clock.ClockInstructionParser
import com.adclock.model.Hand
import com.adclock.services.HandInteractionService
import org.koin.core.component.KoinApiExtension
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class WaitStepsInstruction(selection: List<ClockSelection>, val waitSteps: Int) : HandInstruction(selection) {
    override fun apply(hand: Hand) {
        handService.setPlannedWaitSteps(hand, waitSteps)
    }

    @OptIn(KoinApiExtension::class)
    companion object : ClockInstructionParser<WaitStepsInstruction>, KoinComponent {
        val handService: HandInteractionService by inject()

        override val key: String
            get() = "DLY"

        override fun deserialize(selection: List<ClockSelection>, parameters: String): WaitStepsInstruction {
            require(parameters.isNotBlank()) { "Parameter waitSteps (like 5) expected." }
            return WaitStepsInstruction(selection, parameters.toInt())
        }

        override fun serializeParameters(instruction: WaitStepsInstruction) = instruction.waitSteps.toString()
    }
}
