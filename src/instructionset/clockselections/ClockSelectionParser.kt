package com.adclock.instructionset.clockselections

import com.adclock.model.HandType

interface ClockSelectionParser<T : ClockSelection> {
    fun deserializable(string: String): Boolean

    fun deserialize(hands: Array<HandType>, string: String): T
    fun serializeParameters(selection: T): String

    fun deserialize(string: String): T {
        var clockPart = string
        val hands = mutableListOf<HandType>()
        while (clockPart.isNotBlank() && clockPart.last() in HandType.shortNames) {
            hands += HandType.valueOfShort(clockPart.last())
            clockPart = clockPart.dropLast(1)
        }
        return deserialize(hands.distinct().sorted().toTypedArray().ifEmpty { HandType.values() }, clockPart)
    }

    fun serialize(selection: T): String {
        val hands = selection.hands.joinToString("") { it.short.toString() }
        return serializeParameters(selection) + hands
    }
}