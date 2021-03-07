package com.adclock.instructionset.instructions.hand

import com.adclock.instructionset.clockselections.ClockSelection
import com.adclock.instructionset.instructions.clock.ClockInstructionParser
import com.adclock.model.Hand
import com.adclock.services.HandInteractionService

class SpeedInstruction(selection: List<ClockSelection>, val speed: Int) : HandInstruction(selection) {
    override fun apply(handService: HandInteractionService, hand: Hand) {
        handService.setPlannedSpeed(hand, speed)
    }

    companion object : ClockInstructionParser<SpeedInstruction> {
        override val key: String
            get() = "SPD"

        override fun deserialize(selection: List<ClockSelection>, parameters: String): SpeedInstruction {
            require(parameters.isNotBlank()) { "Parameter speed (like 5) expected." }
            return SpeedInstruction(selection, parameters.toInt())
        }

        override fun serializeParameters(instruction: SpeedInstruction) = instruction.speed.toString()
    }
}
