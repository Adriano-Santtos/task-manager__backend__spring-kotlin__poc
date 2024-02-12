package poc.taskmanager.taskmanagement.createtask.controller

import mu.KotlinLogging
import org.springframework.web.bind.annotation.RestController
import poc.taskmanager.taskmanagement.createtask.CreateTaskRequest
import poc.taskmanager.taskmanagement.createtask.service.CreateTaskService
import poc.taskmanager.taskmanagement.createtask.toDTO
import poc.taskmanager.taskmanagement.createtask.toResponse

@RestController
class CreateTaskController(
    private val service: CreateTaskService
) : CreateTaskAPI {
    private val log = KotlinLogging.logger {}

    override fun create(request: CreateTaskRequest) =
        service.create(request.toDTO()).toResponse().also {
            log.info("CreateTaskController.create, request:$request")
        }
}
