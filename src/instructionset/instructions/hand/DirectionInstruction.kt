package com.adclock.instructionset.instructions.hand

import com.adclock.instructionset.clockselections.ClockSelection
import com.adclock.instructionset.instructions.clock.ClockInstructionParser
import com.adclock.model.Direction
import com.adclock.model.Hand
import com.adclock.services.HandInteractionService
import org.koin.core.component.KoinApiExtension
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class DirectionInstruction(selection: List<ClockSelection>, val direction: Direction) : HandInstruction(selection) {
    override fun apply(hand: Hand) {
        handService.setPlannedDirection(hand, direction)
    }

    @OptIn(KoinApiExtension::class)
    companion object : ClockInstructionParser<DirectionInstruction>, KoinComponent {
        val handService: HandInteractionService by inject()

        override val key: String
            get() = "DIR"

        override fun deserialize(selection: List<ClockSelection>, parameters: String): DirectionInstruction {
            require(parameters.isNotBlank()) { "Parameter direction ${Direction.values().map { it.name }} expected." }
            return DirectionInstruction(selection, Direction.valueOf(parameters))
        }

        override fun serializeParameters(instruction: DirectionInstruction) = instruction.direction.name
    }
}
