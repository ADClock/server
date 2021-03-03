package com.adclock

import com.adclock.routes.version
import io.ktor.application.*
import io.ktor.response.*
import io.ktor.request.*
import io.ktor.routing.*
import io.ktor.http.content.*
import io.ktor.features.*
import org.slf4j.event.*
import io.ktor.server.engine.*
import io.ktor.gson.*
import java.text.DateFormat

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {

    install(CallLogging) {
        level = Level.INFO
        filter { call -> call.request.path().startsWith("/") }
    }

    install(ShutDownUrl.ApplicationCallFeature) {
        exitCodeSupplier = { 0 } // ApplicationCall.() -> Int
    }

    install(ContentNegotiation) {
        gson {
            setDateFormat(DateFormat.LONG)
            setPrettyPrinting()
        }
    }

    routing {

        when {
            isDev -> get("/") {
                // redirect to frontend dev server
                call.respondRedirect("http://localhost:8081")
            }
            isProd -> static("/") {
                // serve frontend with Ktor
                resources("dist")
                resource("/", "dist/index.html")
            }
        }

        // serve assets
        static("assets") {
            resources("assets")
        }

        val apiConfig = environment.config.config("adclock.api")
        route("api") {

            version(apiConfig)
        }

    }

    // Print all Roots
    val root = feature(Routing)
    val allRoutes = allRoutes(root)
    val allRoutesWithMethod = allRoutes.filter { it.selector is HttpMethodRouteSelector }
    allRoutesWithMethod.forEach {
        environment.log.info("route: $it")
    }

    environment.log.info("Testing-Mode: $testing")
}

val Application.envKind get() = environment.config.propertyOrNull("ktor.environment")?.getString()
val Application.isDev get() = envKind != null && envKind == "dev"
val Application.isProd get() = envKind != null && envKind != "dev"

fun allRoutes(root: Route): List<Route> {
    return listOf(root) + root.children.flatMap { allRoutes(it) }
}