package poc.taskmanager.taskmanagement.mapper

import poc.taskmanager.taskmanagement.dto.UpdateTaskDTO
import poc.taskmanager.taskmanagement.request.UpdateTaskRequest

fun UpdateTaskRequest.toDTO() = UpdateTaskDTO(
    title = title,
    description = description,
    status = status,
)
