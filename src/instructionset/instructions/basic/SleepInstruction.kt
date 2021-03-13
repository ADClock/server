package com.adclock.instructionset.instructions.basic

import com.adclock.instructionset.Task
import com.adclock.instructionset.instructions.Instruction
import com.adclock.instructionset.instructions.InstructionParser
import com.adclock.model.ClockWall

class SleepInstruction(val sleep: Int) : Instruction {

    override fun apply(task: Task, wall: ClockWall): Boolean {
        task.sleepUntil = System.currentTimeMillis() + sleep * 1000
        return true
    }

    companion object : InstructionParser<SleepInstruction> {
        override val key: String
            get() = "SLP"

        override fun deserialize(input: String): SleepInstruction {
            require(input.isNotBlank()) { "Parameter sleep time (like 5) expected." }
            return SleepInstruction(input.toInt())
        }

        override fun serialize(instruction: SleepInstruction) = instruction.sleep.toString()
    }

}
