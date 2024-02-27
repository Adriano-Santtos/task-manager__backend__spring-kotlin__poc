package poc.taskmanager.taskmanagement.controller.swagger

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.ArraySchema
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import poc.taskmanager.taskmanagement.request.UpdateTaskRequest
import poc.taskmanager.taskmanagement.response.TaskResponse

interface UpdateTaskAPI {

    @Operation(summary = "Update a task")
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200",
                description = "Task edited with success",
                content = [
                    (
                        Content(
                            mediaType = "application/json",
                            array = (
                                ArraySchema(schema = Schema(implementation = TaskResponse::class))
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
                description = "Task not found",
                content = [Content()],
            ),
        ]
    )
    @PatchMapping("/tasks/{id}")
    fun update(@PathVariable id: Long, @RequestBody request: UpdateTaskRequest): TaskResponse
}
