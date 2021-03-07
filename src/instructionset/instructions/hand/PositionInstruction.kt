package com.adclock.instructionset.instructions.hand

import com.adclock.instructionset.clockselections.ClockSelection
import com.adclock.instructionset.instructions.clock.ClockInstructionParser
import com.adclock.model.Hand
import com.adclock.services.HandInteractionService

class PositionInstruction(
    selection: List<ClockSelection>,
    val position: Int,
    val relative: Boolean = false
) : HandInstruction(selection) {
    override fun apply(handService: HandInteractionService, hand: Hand) {
        handService.setPlannedPosition(hand, position, relative)
    }

    companion object : ClockInstructionParser<PositionInstruction> {
        override val key: String
            get() = "POS"

        override fun deserialize(selection: List<ClockSelection>, parameters: String): PositionInstruction {
            require(parameters.isNotBlank()) { "Parameter position (like 132R) expected." }
            var relative = false
            var positionString = parameters
            if (positionString.last() == 'R') {
                positionString = positionString.dropLast(1)
                relative = true
            } else if (positionString.last() == 'A') {
                positionString = positionString.dropLast(1)
                relative = false
            }
            return PositionInstruction(selection, positionString.toInt(), relative)
        }

        override fun serializeParameters(instruction: PositionInstruction) =
            "${instruction.position}${if (instruction.relative) "R" else "A"}"
    }
}
