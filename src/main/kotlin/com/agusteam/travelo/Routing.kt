package com.agusteam.travelo

import com.agusteam.travelo.models.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.http.content.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    install(StatusPages) {
        exception<IllegalStateException> { call, cause ->
            call.respondText("App in illegal state as ${cause.message}")
        }
    }
    install(ContentNegotiation) {
        json()  // Use Kotlinx Serialization with JSON format
    }
    routing {
        staticResources("/task-ui", "task-ui")

        route("/users") {
            post {
                val userReq = call.receive<UserSignupModel>()
                try {
                    if (userReq.validate()) {
                        call.respond(HttpStatusCode.BadRequest)
                    } else {

                    }
                } catch (e: Exception) {
                    call.respond(HttpStatusCode.BadRequest)

                }
            }
        }






        route("/tasks") {
            get("/lists") {
                call.respond(
                    listOf(
                        TaskModel("cleaning", "Clean the house", Priority.Low),
                        TaskModel("gardening", "Mow the lawn", Priority.Medium),
                        TaskModel("shopping", "Buy the groceries", Priority.High),
                        TaskModel("painting", "Paint the fence", Priority.Medium)
                    )
                )
            }
            get("/byName/{taskName}") {
                val taskName = call.parameters["taskName"]
                if (taskName.isNullOrBlank()) {
                    call.respond(HttpStatusCode.BadRequest)
                    return@get
                } else {
                    val task = TaskRepository.taskByName(taskName)
                    if (task == null) {
                        call.respond(HttpStatusCode.NotFound)
                        return@get
                    }

                    call.respondText(
                        contentType = ContentType.parse("text/html"),
                        text = listOf(task).tasksAsTable()
                    )
                }

            }

            get {
                val tasks = TaskRepository.allTasks()
                call.respondText(
                    contentType = ContentType.parse("text/html"),
                    text = tasks.tasksAsTable()
                )
            }
            get("/byPriority/{priority}") {
                val priorityAsText = call.parameters["priority"]
                if (priorityAsText == null) {
                    call.respond(HttpStatusCode.BadRequest)
                    return@get
                }

                try {
                    val priority = Priority.valueOf(priorityAsText)
                    val tasks = TaskRepository.tasksByPriority(priority)

                    if (tasks.isEmpty()) {
                        call.respond(HttpStatusCode.NotFound)
                        return@get
                    }

                    call.respondText(
                        contentType = ContentType.parse("text/html"),
                        text = tasks.tasksAsTable()
                    )
                } catch (ex: IllegalArgumentException) {
                    call.respond(HttpStatusCode.BadRequest)
                }
            }

            post {
                val formContent = call.receiveParameters()

                val params = Triple(
                    formContent["name"] ?: "",
                    formContent["description"] ?: "",
                    formContent["priority"] ?: ""
                )

                if (params.toList().any { it.isEmpty() }) {
                    call.respond(HttpStatusCode.BadRequest)
                    return@post
                }

                try {
                    val priority = Priority.valueOf(params.third)
                    TaskRepository.addTask(
                        TaskModel(
                            params.first,
                            params.second,
                            priority
                        )
                    )

                    call.respond(HttpStatusCode.NoContent)
                } catch (ex: IllegalArgumentException) {
                    call.respond(HttpStatusCode.BadRequest)
                } catch (ex: IllegalStateException) {
                    call.respond(HttpStatusCode.BadRequest)
                }
            }
        }

    }
}
