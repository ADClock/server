package com.adclock.instructionset.instructions

interface InstructionParser<T : Instruction> {
    val key: String

    fun deserialize(input: String): T

    fun serialize(instruction: T): String
}