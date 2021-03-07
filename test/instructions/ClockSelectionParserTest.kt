package com.adclock.instructions

import com.adclock.instructionset.InstructionSet
import com.adclock.instructionset.clockselections.RangeClockSelection
import com.adclock.instructionset.clockselections.SingleClockSelection
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class ClockSelectionParserTest {

    @Test
    fun parseBasicSelection() {
        val selection = InstructionSet.deserializeSelection("1")
        assertEquals(1, selection.size, "Amount of selection entries")
        assertTrue(selection.first() is SingleClockSelection, "Type of first selection")
        assertEquals("1HM", InstructionSet.serializeSelection(selection), "Serialized String")
    }

    @Test
    fun parseDoubleSelection() {
        val selection = InstructionSet.deserializeSelection("1,5")
        assertEquals(2, selection.size, "Amount of selection entries")
        assertTrue(selection.component1() is SingleClockSelection, "Type of first selection")
        assertTrue(selection.component2() is SingleClockSelection, "Type of second selection")
        assertEquals("1HM,5HM", InstructionSet.serializeSelection(selection), "Serialized String")
    }


    @Test
    fun parseTripleSelection() {
        val selection = InstructionSet.deserializeSelection("1H,5M,7HM")
        assertEquals(3, selection.size, "Amount of selection entries")
        assertTrue(selection.component1() is SingleClockSelection, "Type of first selection")
        assertTrue(selection.component2() is SingleClockSelection, "Type of second selection")
        assertTrue(selection.component3() is SingleClockSelection, "Type of third selection")
        assertEquals("1H,5M,7HM", InstructionSet.serializeSelection(selection), "Serialized String")
    }

    @Test
    fun parseComplexSelection() {
        val selection = InstructionSet.deserializeSelection("1H,5M,7HM,12-14H")
        assertEquals(4, selection.size, "Amount of selection entries")
        assertTrue(selection.component1() is SingleClockSelection, "Type of first selection")
        assertTrue(selection.component2() is SingleClockSelection, "Type of second selection")
        assertTrue(selection.component3() is SingleClockSelection, "Type of third selection")
        assertTrue(selection.component4() is RangeClockSelection, "Type of fourth selection")
        assertEquals("1H,5M,7HM,12-14H", InstructionSet.serializeSelection(selection), "Serialized String")
    }

    @Test(IllegalArgumentException::class)
    fun parseIllegalSingleSelection() {
        InstructionSet.deserializeSelection("A")
    }

    @Test(IllegalArgumentException::class)
    fun parseIllegalRangeSelection() {
        InstructionSet.deserializeSelection("A-B")
    }

    @Test(IllegalArgumentException::class)
    fun parseIllegalDoubleRangeSelection() {
        InstructionSet.deserializeSelection("1-3-4")
    }


    @Test(IllegalArgumentException::class)
    fun parseIllegalRangeBoundsSelection() {
        InstructionSet.deserializeSelection("5-2")
    }

    @Test(IllegalArgumentException::class)
    fun parseIllegalRangeWithHandSelection() {
        InstructionSet.deserializeSelection("5M-2H")
    }

    @Test(IllegalArgumentException::class)
    fun parseIllegalDelimiterSelection() {
        InstructionSet.deserializeSelection("1-2;3")
    }

    @Test(IllegalArgumentException::class)
    fun parseOnlyDelimiterSelection() {
        InstructionSet.deserializeSelection(",")
    }


    @Test(IllegalArgumentException::class)
    fun parseIllegalSpacesSelection() {
        InstructionSet.deserializeSelection("1 ")
    }


    @Test(IllegalArgumentException::class)
    fun parseIllegalSpacesBeforeDelimiterSelection() {
        InstructionSet.deserializeSelection("1 ,4")
    }


    @Test(IllegalArgumentException::class)
    fun parseEmptySelection() {
        InstructionSet.deserializeSelection("")
    }


    @Test(IllegalArgumentException::class)
    fun parseBlankSelection() {
        InstructionSet.deserializeSelection(" ")
    }
}