package com.adclock.routes

import com.adclock.dto.HandUpdateRequest
import com.adclock.services.ClockService
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

fun Route.clock(clockService: ClockService) {
    get("/list") {
        call.respond(clockService.list())
    }

    post("/update") {
        val request = call.receive<HandUpdateRequest>()
        clockService.updateHand(request)
        call.respond(HttpStatusCode.OK, "Update was successful.")
    }
}