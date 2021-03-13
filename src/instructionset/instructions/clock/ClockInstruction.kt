package com.adclock.instructionset.instructions.clock

import com.adclock.instructionset.clockselections.ClockSelection
import com.adclock.instructionset.instructions.wall.WallInstruction
import com.adclock.model.Clock
import com.adclock.model.ClockWall
import com.adclock.util.filter

abstract class ClockInstruction(internal val selection: List<ClockSelection>) : WallInstruction {
    abstract fun apply(clock: Clock)

    override fun apply(wall: ClockWall) {
        wall.clocks.filter(selection).forEach {
            apply(it)
        }
    }
}
