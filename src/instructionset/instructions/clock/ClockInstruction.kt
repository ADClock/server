package com.adclock.instructionset.instructions.clock

import com.adclock.instructionset.clockselections.ClockSelection
import com.adclock.instructionset.instructions.wall.WallInstruction
import com.adclock.model.Clock
import com.adclock.services.ClockInteractionService
import com.adclock.services.WallInteractionService
import com.adclock.util.filter

abstract class ClockInstruction(internal val selection: List<ClockSelection>) : WallInstruction {
    abstract fun apply(clockService: ClockInteractionService, clock: Clock)

    override fun apply(wallService: WallInteractionService) {
        wallService.wall.clocks.filter(selection).forEach {
            apply(wallService.clockService, it)
        }
    }
}
