package poc.taskmanager.taskmanagement.controller

import mu.KotlinLogging
import org.springframework.web.bind.annotation.RestController
import poc.taskmanager.taskmanagement.controller.swagger.CreateTaskAPI
import poc.taskmanager.taskmanagement.mapper.toDTO
import poc.taskmanager.taskmanagement.mapper.toResponse
import poc.taskmanager.taskmanagement.request.CreateTaskRequest
import poc.taskmanager.taskmanagement.response.TaskResponse
import poc.taskmanager.taskmanagement.service.CreateTaskService

@RestController
class CreateTaskController(
    private val service: CreateTaskService
) : CreateTaskAPI {
    private val log = KotlinLogging.logger {}

    override fun create(request: CreateTaskRequest): TaskResponse {
        log.info { "CreateTaskController.create, request: $request" }

        return service.create(request.toDTO()).toResponse()
    }
}
