package dev.udinsi

import com.fasterxml.jackson.databind.SerializationFeature
import io.ktor.application.*
import io.ktor.response.*
import io.ktor.request.*
import io.ktor.routing.*
import io.ktor.http.*
import io.ktor.html.*
import kotlinx.html.*
import kotlinx.css.*
import io.ktor.client.*
import io.ktor.client.engine.apache.*
import io.ktor.features.CallLogging
import io.ktor.features.ContentNegotiation
import io.ktor.features.DefaultHeaders
import io.ktor.jackson.jackson
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import java.lang.Compiler.enable
import java.util.*

data class User(val text : String)

val users = Collections.synchronizedList(mutableListOf(
    User("Udin"),
    User("Nopal")
))

fun Application.main(){
//    install(DefaultHeaders)
//    install(CallLogging)
//    install(Routing) {
//        get("/") {
//            call.respondText("My Example Blog2", ContentType.Text.Html)
//        }
//
//        get("/udin") {
//            call.respondText("ini udin", ContentType.Text.Html)
//        }
//    }
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

//fun Application.module(){
//    install(DefaultHeaders)
//    install(CallLogging)
//    install(Routing) {
//        get("/") {
//            call.respondText("My Example Blog2", ContentType.Text.Html)
//        }
//
//        get("/udin"){
//            call.respondText("ini udin", ContentType.Text.Html)
//        }
//    }
//}

//fun main(args: Array<String>) {
//    embeddedServer(Netty, watchPaths = listOf(""), module = Application::module).start()
//}