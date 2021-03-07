package com.adclock.instructionset.instructions.clock

import com.adclock.instructionset.InstructionSet
import com.adclock.instructionset.clockselections.ClockSelection
import com.adclock.instructionset.instructions.InstructionParser

interface ClockInstructionParser<T : ClockInstruction> : InstructionParser<T> {

    fun deserialize(selection: List<ClockSelection>, parameters: String): T
    fun serializeParameters(instruction: T): String

    override fun deserialize(input: String): T {
        val selection = InstructionSet.deserializeSelection(input.substringBefore(' '))
        return deserialize(selection, input.substringAfter(' ', ""))
    }

    override fun serialize(instruction: T): String {
        return InstructionSet.serializeSelection(instruction.selection) + " " + serializeParameters(instruction)
    }

}