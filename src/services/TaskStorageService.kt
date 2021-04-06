package com.adclock.services

import com.adclock.instructionset.InstructionSet
import com.adclock.instructionset.Task
import com.adclock.instructionset.instructions.Instruction
import com.adclock.util.createOrEmptyFile
import com.adclock.util.readLinesIndexed
import java.io.File

class TaskStorageService(private val taskFolder: File) {

    fun loadTask(name: String): Task {
        val file = getTaskFile(name)

        if (!file.exists())
            throw IllegalArgumentException("File for task $name does not exists.")

        val instructions = mutableListOf<Instruction>()
        file.readLinesIndexed { index, line ->
            try {
                instructions += InstructionSet.deserialize(line)
            } catch (e: Exception) {
                throw IllegalArgumentException("Failed to deserialize ${file.name} line ${index + 1}: ${e.message}")
            }
        }
        return Task(file.nameWithoutExtension, instructions)
    }

    fun saveTask(task: Task) {
        val file = getTaskFile(task.name)
        file.createOrEmptyFile()
        task.instructions.map { InstructionSet.serialize(it) }.forEach { file.appendText(it) }
    }

    fun deleteTask(name: String) {
        val file = getTaskFile(name)

        if (!file.exists())
            throw IllegalArgumentException("File for task $name does not exists.")

        if (!file.delete())
            throw IllegalArgumentException("File for task $name not deletable.")
    }


    fun exists(name: String) = getTaskFile(name).exists()

    fun taskNames(): Array<String> = taskFolder.list { _, name -> name.endsWith(FILE_EXTENSION) }
        ?: throw IllegalStateException("Error loading task names.")


    private fun getTaskFile(name: String) = File(taskFolder, "$name$FILE_EXTENSION")

    companion object {
        private const val FILE_EXTENSION = ".adc"
    }

}