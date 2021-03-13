package com.adclock.routes

import com.adclock.model.ClockWall
import com.adclock.services.WallInteractionService
import io.ktor.application.*
import io.ktor.response.*
import io.ktor.routing.*

fun Route.clock(wallService: WallInteractionService, wall: ClockWall) {
    get("/list") {
        call.respond(wall.clocks)
    }
}