package dev.udinsi

import com.fasterxml.jackson.databind.SerializationFeature
import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.features.ContentNegotiation
import io.ktor.jackson.jackson
import io.ktor.response.respond
import io.ktor.routing.get
import io.ktor.routing.routing
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import java.util.*

data class User(val nama : String)

val users = Collections.synchronizedList(mutableListOf(
    User("Udin"),
    User("Nopal")
))

fun Application.module(){
    install(ContentNegotiation) {
        jackson {
            enable(SerializationFeature.INDENT_OUTPUT) // Pretty Prints the JSON
        }
    }
    routing {
        get("/users") {
            call.respond(mapOf("user" to synchronized(users) { users.toList() }))
        }
    }
}

fun main(args: Array<String>) {
    embeddedServer(
        Netty,
        watchPaths = listOf("solutions/exercise4"),
        port = 8080,
        module = Application::module
    ).apply { start(wait = true) }
}