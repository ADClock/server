package com.adclock.instructionset.clockselections

import com.adclock.model.Clock
import com.adclock.model.HandType

class SingleClockSelection(
    val id: Int,
    override val hands: Array<HandType>
) : ClockSelection {
    override fun selected(clock: Clock) = clock.id == id

    companion object : ClockSelectionParser<SingleClockSelection> {
        override fun deserializable(string: String) = "-" !in string

        override fun deserialize(hands: Array<HandType>, string: String) =
            SingleClockSelection(string.toInt(), hands)

        override fun serializeParameters(selection: SingleClockSelection) = selection.id.toString()
    }
}