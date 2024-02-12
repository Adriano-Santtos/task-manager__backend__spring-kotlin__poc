package poc.taskmanager.taskmanagement.createtask.service

import mu.KotlinLogging
import org.springframework.stereotype.Service
import poc.taskmanager.taskmanagement.common.entities.TaskEntity
import poc.taskmanager.taskmanagement.common.repository.TaskRepository
import poc.taskmanager.taskmanagement.createtask.dto.CreateTaskDTO
import poc.taskmanager.taskmanagement.createtask.toEntity

const val EMPTY_VALUE = "EMPTY"

@Service
class CreateTaskService(
    private val repository: TaskRepository
) {
    private val log = KotlinLogging.logger {}

    fun create(dto: CreateTaskDTO): TaskEntity {
        val description = isDescriptionFilled(dto.description)

        return repository.save(dto.toEntity(description = description))
            .also { log.info { "CreateTaskService.create, dto: $dto" } }
    }

    private fun isDescriptionFilled(description: String?) =
        description?.takeIf { it.isNotBlank() } ?: EMPTY_VALUE
}
