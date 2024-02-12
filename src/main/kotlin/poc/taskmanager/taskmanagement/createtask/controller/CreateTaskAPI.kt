package poc.taskmanager.taskmanagement.createtask.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.ArraySchema
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import poc.taskmanager.taskmanagement.createtask.CreateTaskRequest
import poc.taskmanager.taskmanagement.createtask.CreateTaskResponse

interface CreateTaskAPI {

    @Operation(summary = "Create a new task")
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200",
                description = "Task created",
                content = [
                    (
                        Content(
                            mediaType = "application/json",
                            array = (
                                ArraySchema(schema = Schema(implementation = CreateTaskResponse::class))
                                )
                        )
                        )
                ],
            ),
            ApiResponse(
                responseCode = "400",
                description = "Bad request",
                content = [Content()],
            ),
            ApiResponse(
                responseCode = "404",
                description = "Resource not found",
                content = [Content()],
            ),
        ]
    )
    @PostMapping("/tasks")
    fun create(@RequestBody request: CreateTaskRequest): CreateTaskResponse
}
