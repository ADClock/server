package com.adclock.services

import com.adclock.instructionset.InstructionSet
import com.adclock.instructionset.Task

class TaskService(private val storageService: TaskStorageService) {

    private val taskNames = mutableListOf<String>()
    private val tasks = mutableListOf<Task>()

    init {
        reloadFromFiles()
    }

    fun createTask(taskName: String, instructions: List<String> = emptyList()) {
        if (storageService.exists(taskName))
            throw IllegalArgumentException("A task named $taskName already exists.")

        val task = Task(taskName)
        tasks += task
        taskNames += task.name

        if (instructions.isNotEmpty())
            setInstructions(taskName, instructions)
    }

    fun removeTask(taskName: String) {
        val task = getTask(taskName)
        tasks -= task
        taskNames.remove(task.name)
        storageService.deleteTask(task.name)
    }

    fun setInstructions(taskName: String, instructions: List<String>) {
        val task = getTask(taskName)

        if (instructions.isEmpty())
            throw IllegalArgumentException("Can not set empty instruction list. Try to remove the task instead.")

        val parsedInstructions = instructions.mapIndexed { index, instruction ->
            try {
                InstructionSet.deserialize(instruction)
            } catch (e: Exception) {
                e.printStackTrace()
                throw IllegalArgumentException("Parsing of instruction $index failed: ${e.message}")
            }
        }

        task.instructions.clear()
        task.instructions += parsedInstructions

        storageService.saveTask(task)
    }

    fun getInstructions(taskName: String) = getTask(taskName).instructions.map { InstructionSet.serialize(it) }

    fun getTask(name: String) = tasks.singleOrNull { it.name == name } ?: storageService.loadTask(name).also { tasks += it }

    fun getTaskNameList() = taskNames.toList()

    fun reloadFromFiles() {
        tasks.clear()
        taskNames.clear()

        taskNames += storageService.taskNames()
    }
}