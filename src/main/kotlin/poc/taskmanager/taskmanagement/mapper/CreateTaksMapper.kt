package poc.taskmanager.taskmanagement.mapper

import poc.taskmanager.taskmanagement.dto.CreateTaskDTO
import poc.taskmanager.taskmanagement.entities.TaskEntity
import poc.taskmanager.taskmanagement.enums.TaskStatusEnum.TODO
import poc.taskmanager.taskmanagement.request.CreateTaskRequest
import poc.taskmanager.taskmanagement.response.TaskResponse

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

fun TaskEntity.toResponse() = TaskResponse(
    title, description, status
)
