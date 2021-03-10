package com.adclock.instructionset.instructions.basic

import com.adclock.instructionset.Task
import com.adclock.instructionset.instructions.Instruction
import com.adclock.instructionset.instructions.InstructionParser
import com.adclock.model.ClockWall

class RepeatInstruction : Instruction {

    override fun apply(task: Task, wall: ClockWall): Boolean {
        task.restart()
        return false
    }


    companion object : InstructionParser<RepeatInstruction> {
        override val key: String
            get() = "RPT"

        override fun deserialize(input: String): RepeatInstruction {
            require(input.isBlank()) { "No parameters for repeat instruction expected, but was $input." }
            return RepeatInstruction()
        }

        override fun serialize(instruction: RepeatInstruction) = ""
    }

}
