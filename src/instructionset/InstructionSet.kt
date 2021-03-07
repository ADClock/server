@file:Suppress("UNCHECKED_CAST")

package com.adclock.instructionset

import com.adclock.instructionset.clockselections.ClockSelection
import com.adclock.instructionset.clockselections.ClockSelectionParser
import com.adclock.instructionset.instructions.Instruction
import com.adclock.instructionset.instructions.InstructionParser
import com.adclock.util.findSubclasses
import kotlin.reflect.full.companionObjectInstance

object InstructionSet {

    private val instructionParsers = findSubclasses(this::class.java.`package`.name, Instruction::class)
        .associateWith { it.companionObjectInstance as? InstructionParser<*> }

    private val selectionParsers = findSubclasses(this::class.java.`package`.name, ClockSelection::class)
        .associateWith { it.companionObjectInstance as? ClockSelectionParser<*> }

    fun deserialize(input: String): Instruction {
        val instruction = input.take(3)
        val parser = instructionParsers.values.find { instruction == it?.key }
            ?: throw IllegalArgumentException("No parser found for instruction $instruction.")

        if (input.length > 3 && input[3] != ' ')
            throw IllegalArgumentException("Blank after instruction expected.")

        return parser.deserialize(input.drop(4))
    }


    fun serialize(instruction: Instruction): String {
        val parser = instructionParsers[instruction::class]
            ?: throw IllegalArgumentException("No parser found for instruction type ${instruction::class.simpleName}.")
        parser as InstructionParser<Instruction>
        return "${parser.key.padEnd(3)} ${parser.serialize(instruction)}".trim()
    }

    fun deserializeSelection(selection: String) =
        selection.split(",").map { split ->
            selectionParsers.values.find { it?.deserializable(split) ?: false }?.deserialize(split)
                ?: throw IllegalArgumentException("No parser found for selection part $split")
        }


    fun serializeSelection(selection: ClockSelection): String {
        val parser = selectionParsers[selection::class]
            ?: throw IllegalArgumentException("No parser found for instruction type ${selection::class.simpleName}.")
        parser as ClockSelectionParser<ClockSelection>
        return parser.serialize(selection)
    }

    fun serializeSelection(selections: List<ClockSelection>) = selections.joinToString(",") { serializeSelection(it) }
}