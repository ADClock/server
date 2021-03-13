package com.adclock.services

import com.adclock.instructionset.InstructionSet
import com.adclock.instructionset.Task

class TaskService {

    private val tasks = mutableListOf<Task>()

    init {
        reloadFromFiles()
    }

    fun createTask(taskName: String, instructions: List<String> = emptyList()) {
        // TODO Check if tasks exists
        tasks += Task(taskName)

        if (instructions.isNotEmpty())
            setInstructions(taskName, instructions)
    }

    fun removeTask(taskName: String) {
        val task = getTask(taskName)
        tasks -= task
    }

    fun setInstructions(taskName: String, instructions: List<String>) {
        val task = getTask(taskName)

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
    }

    fun getInstructions(taskName: String) = getTask(taskName).instructions.map { InstructionSet.serialize(it) }

    fun getTask(name: String) =
        tasks.singleOrNull { it.name == name } ?: throw IllegalArgumentException("No task found for name $name.")

    fun getTaskNameList() = tasks.map { it.name }

    fun reloadFromFiles() {
        // TODO Reload Tasks from files
    }
}