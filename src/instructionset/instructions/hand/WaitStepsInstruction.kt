package com.adclock.instructionset.instructions.hand

import com.adclock.instructionset.clockselections.ClockSelection
import com.adclock.instructionset.instructions.clock.ClockInstructionParser
import com.adclock.model.Hand
import com.adclock.services.HandInteractionService

class WaitStepsInstruction(selection: List<ClockSelection>, val waitSteps: Int) : HandInstruction(selection) {
    override fun apply(handService: HandInteractionService, hand: Hand) {
        handService.setPlannedWaitSteps(hand, waitSteps)
    }

    companion object : ClockInstructionParser<WaitStepsInstruction> {
        override val key: String
            get() = "DLY"

        override fun deserialize(selection: List<ClockSelection>, parameters: String): WaitStepsInstruction {
            require(parameters.isNotBlank()) { "Parameter waitSteps (like 5) expected." }
            return WaitStepsInstruction(selection, parameters.toInt())
        }

        override fun serializeParameters(instruction: WaitStepsInstruction) = instruction.waitSteps.toString()
    }
}
