package poc.taskmanager.taskmanagement.createtask

import poc.taskmanager.taskmanagement.common.entities.TaskEntity
import poc.taskmanager.taskmanagement.createtask.TaskStatusEnum.TODO
import poc.taskmanager.taskmanagement.createtask.dto.CreateTaskDTO

fun CreateTaskRequest.toDTO() = CreateTaskDTO(
    title, description
)

fun CreateTaskDTO.toEntity(
    status: String = TODO.name,
    description: String?,
) = TaskEntity(
    title = title,
    description = description,
    status = status
)

fun TaskEntity.toResponse() = CreateTaskResponse(
    title, description, status
)
