package poc.taskmanager.taskmanagement.controller

import mu.KotlinLogging
import org.springframework.web.bind.annotation.RestController
import poc.taskmanager.taskmanagement.controller.swagger.DeleteTaskAPI
import poc.taskmanager.taskmanagement.service.DeleteTaskService

@RestController
class DeleteTaskController(
    private val service: DeleteTaskService
) : DeleteTaskAPI {
    private val log = KotlinLogging.logger {}

    override fun delete(id: Long) {
        log.info { "DeleteTaskController.delete, id: $id" }

        return service.delete(id)
    }
}
