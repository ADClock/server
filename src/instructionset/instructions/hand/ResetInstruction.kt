package com.adclock.instructionset.instructions.hand

import com.adclock.instructionset.clockselections.ClockSelection
import com.adclock.instructionset.instructions.clock.ClockInstructionParser
import com.adclock.model.Hand
import com.adclock.services.HandInteractionService

class ResetInstruction(selection: List<ClockSelection>) : HandInstruction(selection) {
    override fun apply(handService: HandInteractionService, hand: Hand) {
        handService.resetPlanned(hand)
    }

    companion object : ClockInstructionParser<ResetInstruction> {
        override val key: String
            get() = "RST"

        override fun deserialize(selection: List<ClockSelection>, parameters: String): ResetInstruction {
            if (parameters.isNotBlank()) throw IllegalArgumentException("No parameters for reset instruction expected.")
            return ResetInstruction(selection)
        }

        override fun serializeParameters(instruction: ResetInstruction) = ""
    }
}
