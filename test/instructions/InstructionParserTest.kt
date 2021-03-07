package com.adclock.instructions

import com.adclock.instructionset.InstructionSet
import com.adclock.instructionset.instructions.basic.RepeatInstruction
import com.adclock.instructionset.instructions.basic.SleepInstruction
import com.adclock.instructionset.instructions.clock.CalibrateInstruction
import com.adclock.instructionset.instructions.hand.*
import com.adclock.instructionset.instructions.wall.RunInstruction
import kotlin.reflect.KClass
import kotlin.test.Test
import kotlin.test.assertEquals

class InstructionParserTest {

    private fun assertInstructionParse(string: String) {
        val instruction = InstructionSet.deserialize(string)
        val serialized = InstructionSet.serialize(instruction)
        assertEquals(string, serialized, "Instruction deserialized and serialized")
    }

    private fun assertInstructionType(string: String, type: KClass<*>) {
        val instruction = InstructionSet.deserialize(string)
        assertEquals(type, instruction::class, "Instruction type")
    }

    @Test
    fun parseCalibrateInstruction() {
        assertInstructionType("CAL 1", CalibrateInstruction::class)
        assertInstructionParse("CAL 12-14HM,15M")
    }

    @Test
    fun parseDirectionInstruction() {
        assertInstructionType("DIR 1 FORWARD", DirectionInstruction::class)
        assertInstructionParse("DIR 1H FORWARD")
        assertInstructionParse("DIR 1H BACKWARD")
    }

    @Test
    fun parsePositionInstruction() {
        assertInstructionType("POS 1 12", PositionInstruction::class)
        assertInstructionParse("POS 1H 12R")
        assertInstructionParse("POS 1H 12A")
    }

    @Test
    fun parseResetInstruction() {
        assertInstructionType("RST 1", ResetInstruction::class)
        assertInstructionParse("RST 1H")
    }

    @Test
    fun parseSpeedInstruction() {
        assertInstructionType("SPD 1 12", SpeedInstruction::class)
        assertInstructionParse("SPD 1H 12")
        assertInstructionParse("SPD 1H 12")
    }

    @Test
    fun parseWaitStepsInstruction() {
        assertInstructionType("DLY 1 12", WaitStepsInstruction::class)
        assertInstructionParse("DLY 1H 12")
        assertInstructionParse("DLY 1H 12")
    }


    @Test
    fun parseRunInstruction() {
        assertInstructionType("RUN", RunInstruction::class)
        assertInstructionParse("RUN")
    }

    @Test
    fun parseSleepInstruction() {
        assertInstructionType("SLP 42", SleepInstruction::class)
        assertInstructionParse("SLP 12")
    }

    @Test
    fun parseRepeatInstruction() {
        assertInstructionType("RPT", RepeatInstruction::class)
        assertInstructionParse("RPT")
    }

    @Test(IllegalArgumentException::class)
    fun parseEmptyInstruction() {
        InstructionSet.deserialize("")
    }

    @Test(IllegalArgumentException::class)
    fun parseBlankInstruction() {
        InstructionSet.deserialize("             ")
    }


    @Test(IllegalArgumentException::class)
    fun parseUnknownInstruction() {
        InstructionSet.deserialize("R2D")
    }

    @Test(IllegalArgumentException::class)
    fun parseIllegal4thCharInstruction() {
        InstructionSet.deserialize("CAL_")
    }
}