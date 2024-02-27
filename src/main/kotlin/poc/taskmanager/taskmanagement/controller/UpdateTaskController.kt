package poc.taskmanager.taskmanagement.controller

import mu.KotlinLogging
import org.springframework.web.bind.annotation.RestController
import poc.taskmanager.taskmanagement.controller.swagger.UpdateTaskAPI
import poc.taskmanager.taskmanagement.mapper.toDTO
import poc.taskmanager.taskmanagement.mapper.toResponse
import poc.taskmanager.taskmanagement.request.UpdateTaskRequest
import poc.taskmanager.taskmanagement.response.TaskResponse
import poc.taskmanager.taskmanagement.service.UpdateTaskService

@RestController
class UpdateTaskController(
    private val service: UpdateTaskService
) : UpdateTaskAPI {
    private val log = KotlinLogging.logger {}

    override fun update(id: Long, request: UpdateTaskRequest): TaskResponse {
        log.info("UpdateTaskController.update, id:$id, request:$request")

        return service.update(id, request.toDTO()).toResponse()
    }
}
