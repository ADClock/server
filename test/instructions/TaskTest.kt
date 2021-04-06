package com.adclock.instructions

import com.adclock.instructionset.Task
import com.adclock.instructionset.instructions.wall.RunInstruction
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class TaskTest {

    @Test
    fun createEmptyTask() {
        val task = Task("dummy")
        assertEquals("dummy", task.name, "Task name")
        assertEquals(0, task.instructions.size, "Instruction set size")
    }

    @Test
    fun createTaskWithSingleInstruction() {
        val task = Task("dummy", mutableListOf(RunInstruction()))
        assertEquals("dummy", task.name, "Task name")
        assertEquals(1, task.instructions.size, "Instruction set size")
        assertTrue(task.instructions.first() is RunInstruction, "First Instruction type must be RunInstruction")
    }
}