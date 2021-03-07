package com.adclock.instructionset.instructions.clock

import com.adclock.instructionset.clockselections.ClockSelection
import com.adclock.model.Clock
import com.adclock.services.ClockInteractionService

class CalibrateInstruction(selection: List<ClockSelection>) : ClockInstruction(selection) {
    override fun apply(clockService: ClockInteractionService, clock: Clock) {
        clockService.sendInit(clock)
    }

    companion object : ClockInstructionParser<CalibrateInstruction> {
        override val key: String
            get() = "CAL"

        override fun deserialize(selection: List<ClockSelection>, parameters: String): CalibrateInstruction {
            require(parameters.isBlank()) { "No parameters for calibrate instruction expected, but was $parameters." }
            return CalibrateInstruction(selection)
        }

        override fun serializeParameters(instruction: CalibrateInstruction) = ""
    }
}
