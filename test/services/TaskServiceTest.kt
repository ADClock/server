package com.adclock.services

import com.adclock.instructionset.instructions.wall.RunInstruction
import org.junit.After
import org.junit.Before
import org.junit.rules.TemporaryFolder
import kotlin.test.Test
import kotlin.test.assertEquals

class TaskServiceTest {

    val tempFolder = TemporaryFolder()

    @Before
    fun prepareTempFolder() {
        tempFolder.create()
    }

    @After
    fun cleanupTempFolder() {
        tempFolder.create()
    }

    private fun getTaskStorageService(): TaskStorageService {
        return TaskStorageService(tempFolder.newFolder("tasks/"))
    }

    @Test
    fun shouldReturnEmptyTaskNameList() {
        val service = TaskService(getTaskStorageService())
        assertEquals(emptyList(), service.getTaskNameList(), "Task list")
    }

    @Test
    fun shouldReturnOneTaskNameList() {
        val service = TaskService(getTaskStorageService())
        service.createTask("Foo")
        assertEquals(listOf("Foo"), service.getTaskNameList(), "Task list")
    }

    @Test
    fun addTaskWithNoInstructions() {
        val service = TaskService(getTaskStorageService())
        service.createTask("Foo")
        val task = service.getTask("Foo")
        assertEquals("Foo", task.name, "Task name")
        assertEquals(0, task.instructions.size, "Task instructions list size")
    }


    @Test
    fun addTaskWithSingleInstruction() {
        val service = TaskService(getTaskStorageService())
        service.createTask("Simple", listOf("RUN"))
        val task = service.getTask("Simple")
        assertEquals(1, task.instructions.size, "Task instructions list size")
        assertEquals(RunInstruction::class, task.instructions.first()::class, "First task instruction")
    }
}