package com.adclock.instructionset.instructions.hand

import com.adclock.instructionset.clockselections.ClockSelection
import com.adclock.instructionset.instructions.clock.ClockInstructionParser
import com.adclock.model.Hand
import com.adclock.services.HandInteractionService
import org.koin.core.component.KoinApiExtension
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class ResetInstruction(selection: List<ClockSelection>) : HandInstruction(selection) {
    override fun apply(hand: Hand) {
        handService.resetPlanned(hand)
    }

    @OptIn(KoinApiExtension::class)
    companion object : ClockInstructionParser<ResetInstruction>, KoinComponent {
        val handService: HandInteractionService by inject()

        override val key: String
            get() = "RST"

        override fun deserialize(selection: List<ClockSelection>, parameters: String): ResetInstruction {
            if (parameters.isNotBlank()) throw IllegalArgumentException("No parameters for reset instruction expected.")
            return ResetInstruction(selection)
        }

        override fun serializeParameters(instruction: ResetInstruction) = ""
    }
}
