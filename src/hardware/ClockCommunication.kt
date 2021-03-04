package com.adclock.hardware

import com.adclock.model.Clock
import com.adclock.util.serialize
import com.pi4j.io.i2c.I2CBus
import com.pi4j.io.i2c.I2CDevice
import com.pi4j.io.i2c.I2CFactory

object ClockCommunication {

    private val i2c = I2CFactory.getInstance(I2CBus.BUS_1)
    private val clockDevices = mutableMapOf<Clock, I2CDevice>()

    fun sendUpdate(clock: Clock) = with(clockDevices[clock]) {
        requireNotNull(this) { "No device found for clock $clock" }
        write(0x02)
        write(clock.serialize())
    }

    fun sendInit(clock: Clock) = with(clockDevices[clock]) {
        requireNotNull(this) { "No device found for clock $clock" }
        write(0x01)
    }

    fun setAddress(clock: Clock, address: Int) {
        clockDevices[clock] = i2c.getDevice(address)
    }

    fun removeClock(clock: Clock) {
        clockDevices -= clock
    }

    fun addresses() = clockDevices.mapValues { it.value.address }

}