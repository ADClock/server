package com.adclock.instructionset.instructions.clock

import com.adclock.instructionset.clockselections.ClockSelection
import com.adclock.model.Clock
import com.adclock.services.ClockInteractionService
import org.koin.core.component.KoinApiExtension
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class CalibrateInstruction(selection: List<ClockSelection>) : ClockInstruction(selection) {
    override fun apply(clock: Clock) {
        clockService.sendInit(clock)
    }

    @OptIn(KoinApiExtension::class)
    companion object : ClockInstructionParser<CalibrateInstruction>, KoinComponent {
        val clockService: ClockInteractionService by inject()

        override val key: String
            get() = "CAL"


        override fun deserialize(selection: List<ClockSelection>, parameters: String): CalibrateInstruction {
            require(parameters.isBlank()) { "No parameters for calibrate instruction expected, but was $parameters." }
            return CalibrateInstruction(selection)
        }

        override fun serializeParameters(instruction: CalibrateInstruction) = ""
    }
}
