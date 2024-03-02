package poc.taskmanager.taskmanagement.builder

import poc.taskmanager.taskmanagement.dto.CreateTaskDTO
import java.util.UUID

fun createTaskDTOBuilder(
    title: String = UUID.randomUUID().toString(),
    description: String? = UUID.randomUUID().toString(),
) = CreateTaskDTO(
    title = title,
    description = description,
)
