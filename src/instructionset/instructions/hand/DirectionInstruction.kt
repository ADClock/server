package com.adclock.instructionset.instructions.hand

import com.adclock.instructionset.clockselections.ClockSelection
import com.adclock.instructionset.instructions.clock.ClockInstructionParser
import com.adclock.model.Direction
import com.adclock.model.Hand
import com.adclock.services.HandInteractionService

class DirectionInstruction(selection: List<ClockSelection>, val direction: Direction) : HandInstruction(selection) {
    override fun apply(handService: HandInteractionService, hand: Hand) {
        handService.setPlannedDirection(hand, direction)
    }

    companion object : ClockInstructionParser<DirectionInstruction> {
        override val key: String
            get() = "DIR"

        override fun deserialize(selection: List<ClockSelection>, parameters: String): DirectionInstruction {
            require(parameters.isNotBlank()) { "Parameter direction ${Direction.values().map { it.name }} expected." }
            return DirectionInstruction(selection, Direction.valueOf(parameters))
        }

        override fun serializeParameters(instruction: DirectionInstruction) = instruction.direction.name
    }
}
