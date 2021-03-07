package com.adclock.instructionset.clockselections

import com.adclock.model.Clock
import com.adclock.model.HandType

class RangeClockSelection(
    val range: IntRange,
    override val hands: Array<HandType>
) : ClockSelection {
    override fun selected(clock: Clock) = clock.id in range

    companion object : ClockSelectionParser<RangeClockSelection> {
        override fun deserializable(string: String) = "-" in string

        override fun deserialize(hands: Array<HandType>, string: String): RangeClockSelection {
            val from = string.substringBefore("-")
            val to = string.substringAfter("-")
            val range = from.toInt()..to.toInt()
            require(!range.isEmpty()) { "Range ${range.first} to ${range.last} does not select any clocks." }
            return RangeClockSelection(range, hands)
        }

        override fun serializeParameters(selection: RangeClockSelection) = "${selection.range.first}-${selection.range.last}"
    }
}