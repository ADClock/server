package com.adclock.instructions

import com.adclock.instructionset.Executor
import com.adclock.instructionset.Task
import com.adclock.instructionset.instructions.wall.RunInstruction
import com.adclock.model.ClockWall
import com.adclock.services.ClockInteractionService
import com.adclock.services.HandInteractionService
import com.adclock.services.WallInteractionService
import org.junit.Rule
import org.junit.Test
import org.koin.dsl.module
import org.koin.test.KoinTestRule
import kotlin.test.Ignore
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class ExecutorTest  {

    @get:Rule
    val koinTestRule = KoinTestRule.create {
        // TODO Schön wenn das da ist, aber es wäre besser wenn wir hier das normale Module verwenden
        val module = module {
            single { WallInteractionService(ClockInteractionService(HandInteractionService())) }
        }
        modules(module)
    }


    @Test
    fun createExecutor() {
        val executor = Executor(ClockWall(3, 8))
        assertFalse(executor.active, "Executor must be inactive")
        assertFalse(executor.pendingTasks, "Executor pending tasks must be false")
    }

    @Test(expected = IllegalStateException::class)
    fun runExecutorWithNoActiveTask() {
        val executor = Executor(ClockWall(3, 8))
        assertFalse(executor.active, "Executor must be inactive")
        executor.executeStep()
    }

    @Ignore // TODO Remove if ClockCommunication is mocked
    @Test
    fun addAndCompleteSingleTaskToExecutor() {
        val executor = Executor(ClockWall(3, 8))
        executor.queueTask(Task("dummy", mutableListOf(RunInstruction())))
        assertTrue(executor.active, "Executor must be active")
        executor.executeStep()
        assertFalse(executor.active, "Executor must be inactive by now")
    }
}