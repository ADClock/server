package com.adclock.instructionset

import com.adclock.instructionset.instructions.Instruction

data class Task(val name: String, val instructions: MutableList<Instruction> = mutableListOf())