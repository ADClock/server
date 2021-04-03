package com.adclock.services

import com.adclock.instructionset.instructions.wall.RunInstruction
import kotlin.test.Test
import kotlin.test.assertEquals

class TaskServiceTest {

    @Test
    fun shouldReturnEmptyTaskNameList() {
        val service = TaskService(TaskStorageService())
        assertEquals(emptyList(), service.getTaskNameList(), "Task list")
    }

    @Test
    fun shouldReturnOneTaskNameList() {
        val service = TaskService(TaskStorageService())
        service.createTask("Foo")
        assertEquals(listOf("Foo"), service.getTaskNameList(), "Task list")
    }

    @Test
    fun addTaskWithNoInstructions() {
        val service = TaskService(TaskStorageService())
        service.createTask("Foo")
        val task = service.getTask("Foo")
        assertEquals("Foo", task.name, "Task name")
        assertEquals(0, task.instructions.size, "Task instructions list size")
    }


    @Test
    fun addTaskWithSingleInstruction() {
        val service = TaskService(TaskStorageService())
        service.createTask("Simple", listOf("RUN"))
        val task = service.getTask("Simple")
        assertEquals(1, task.instructions.size, "Task instructions list size")
        assertEquals(RunInstruction::class, task.instructions.first()::class, "First task instruction")
    }
}