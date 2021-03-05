package com.adclock.routes

import com.adclock.services.WallInteractionService
import io.ktor.application.*
import io.ktor.response.*
import io.ktor.routing.*

fun Route.clock(wallService: WallInteractionService) {
    get("/list") {
        call.respond(wallService.wall.clocks)
    }
}