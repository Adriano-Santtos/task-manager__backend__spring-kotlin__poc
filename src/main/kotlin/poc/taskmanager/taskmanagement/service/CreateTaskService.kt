package poc.taskmanager.taskmanagement.service

import mu.KotlinLogging
import org.springframework.stereotype.Service
import poc.taskmanager.taskmanagement.dto.CreateTaskDTO
import poc.taskmanager.taskmanagement.entities.TaskEntity
import poc.taskmanager.taskmanagement.mapper.toEntity
import poc.taskmanager.taskmanagement.repository.TaskRepository

const val EMPTY_VALUE = "EMPTY"

@Service
class CreateTaskService(
    private val repository: TaskRepository
) {
    private val log = KotlinLogging.logger {}

    fun create(dto: CreateTaskDTO): TaskEntity {
        log.info { "CreateTaskService.create, dto: $dto" }

        val description = isDescriptionFilled(dto.description)

        return repository.save(dto.toEntity(description = description))
    }

    private fun isDescriptionFilled(description: String?) =
        description?.takeIf { it.isNotBlank() } ?: EMPTY_VALUE
}
