package poc.taskmanager.taskmanagement.service

import mu.KotlinLogging
import org.springframework.beans.BeanWrapperImpl
import org.springframework.stereotype.Service
import poc.taskmanager.taskmanagement.dto.UpdateTaskDTO
import poc.taskmanager.taskmanagement.entities.TaskEntity
import poc.taskmanager.taskmanagement.enums.TaskStatusEnum
import poc.taskmanager.taskmanagement.exceptions.StatusNotAllowedException
import poc.taskmanager.taskmanagement.repository.TaskRepository

@Service
class UpdateTaskService(private val repository: TaskRepository) {
    val log = KotlinLogging.logger {}

    fun update(id: Long, dto: UpdateTaskDTO): TaskEntity {
        val existingEntity = repository.getReferenceById(id)

        isStatusAvailableToBeUpdated(dto.status)
        copyNonNullProperties(dto, existingEntity)

        return repository.save(existingEntity).also {
            log.info { "UpdateTaskService.update, entityUpdated:$it" }
        }
    }

    private fun copyNonNullProperties(source: Any, target: Any) {
        val sourceBean = BeanWrapperImpl(source)
        val targetBean = BeanWrapperImpl(target)

        sourceBean.propertyDescriptors
            .filter { it.readMethod != null && sourceBean.getPropertyValue(it.name) != null }
            .forEach { descriptor ->
                val propertyName = descriptor.name
                val sourceValue = sourceBean.getPropertyValue(propertyName)
                targetBean.setPropertyValue(propertyName, sourceValue)
            }
    }
}
