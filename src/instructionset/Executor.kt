package com.adclock.instructionset

import com.adclock.model.ClockWall

class Executor(val wall: ClockWall) {

    private val queue = mutableListOf<Task>()
    private val stack = mutableListOf<StackEntry>()
    private var delayUntil: Long = 0


    val active: Boolean
        get() = stack.isNotEmpty()

    val pendingTasks: Boolean
        get() = queue.isNotEmpty()


    fun queueTask(task: Task) {
        queue += task
        if (!active)
            startNextQueuedTask()
    }

    fun executeStep() {
        if (!active)
            throw IllegalStateException("Not active. Can not execute next step.")

        if (delayUntil > System.currentTimeMillis())
            return

        stack.last().run {
            if (currentInstruction.apply(this@Executor, wall))
                current++

            if (completed)
                removeLatestTaskFromStack()
        }
    }

    private fun removeLatestTaskFromStack() {
        stack.removeLast()
        if (stack.isNotEmpty()) {
            stack.last().current++ // Subcall is complete.

            // if previous task is complete remove from stack to
            if (stack.last().completed)
                return removeLatestTaskFromStack()
        }

        // Check if pending task exists and start it
        if (!active && pendingTasks)
            startNextQueuedTask()
    }

    private fun startNextQueuedTask() {
        if (active)
            throw IllegalStateException("Task already active. Can not run tasks in parallel.")

        if (!pendingTasks)
            throw IllegalStateException("No task queued.")

        startSubTask(queue.removeFirst())
    }

    internal fun startSubTask(task: Task) {
        if (task.instructions.isEmpty())
            throw IllegalArgumentException("The Task ${task.name} has no instructions.")

        if (stack.any { it.task.name == task.name })
            throw IllegalArgumentException("Loop detected. Can not start task ${task.name} because its already in program stack.")

        stack += StackEntry(task)
    }

    /**
     * Restarts the execution.
     * There are two modes:
     * - restart current task = restarts only current executed task
     * - restart from begin = clear hole program stack and restarts very first task
     *
     * @param currentTask Boolean
     */
    internal fun restartTask(currentTask: Boolean = false) {
        if (!active)
            throw IllegalStateException("No Task active. Can not restart current task because there is no one.")

        // Remove all Stack Entries. Restart first task
        while (!currentTask && stack.size > 1)
            stack.removeLast()

        stack.last().current = 0
    }

    internal fun delay(delay: Int) {
        delayUntil = System.currentTimeMillis() + delay * 1000
    }
}