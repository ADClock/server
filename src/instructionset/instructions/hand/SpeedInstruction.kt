package com.adclock.instructionset.instructions.hand

import com.adclock.instructionset.clockselections.ClockSelection
import com.adclock.instructionset.instructions.clock.ClockInstructionParser
import com.adclock.model.Hand
import com.adclock.services.HandInteractionService
import org.koin.core.component.KoinApiExtension
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class SpeedInstruction(selection: List<ClockSelection>, val speed: Int) : HandInstruction(selection) {
    override fun apply(hand: Hand) {
        handService.setPlannedSpeed(hand, speed)
    }

    @OptIn(KoinApiExtension::class)
    companion object : ClockInstructionParser<SpeedInstruction>, KoinComponent {
        val handService: HandInteractionService by inject()

        override val key: String
            get() = "SPD"

        override fun deserialize(selection: List<ClockSelection>, parameters: String): SpeedInstruction {
            require(parameters.isNotBlank()) { "Parameter speed (like 5) expected." }
            return SpeedInstruction(selection, parameters.toInt())
        }

        override fun serializeParameters(instruction: SpeedInstruction) = instruction.speed.toString()
    }
}
