package poc.taskmanager.taskmanagement.builder

import poc.taskmanager.taskmanagement.entities.TaskEntity
import poc.taskmanager.taskmanagement.enums.TaskStatusEnum

fun taskEntityBuilder(
    id: Long = 1L,
    title: String = "Old Title",
    description: String? = "Old Description",
    status: String = TaskStatusEnum.TODO.name
) = TaskEntity(
    id, title, description, status
)
