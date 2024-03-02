package poc.taskmanager.taskmanagement.controller.swagger

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.ArraySchema
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import poc.taskmanager.taskmanagement.response.TaskResponse

interface DeleteTaskAPI {

    @Operation(summary = "Delete a task")
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200",
                description = "Task deleted with success",
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
    @DeleteMapping("/tasks/{id}")
    fun delete(@PathVariable id: Long)
}
