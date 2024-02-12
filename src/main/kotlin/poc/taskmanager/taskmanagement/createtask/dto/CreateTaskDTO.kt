package poc.taskmanager.taskmanagement.createtask.dto

import org.apache.commons.lang3.StringUtils.EMPTY

data class CreateTaskDTO(
    val title: String,
    val description: String? = EMPTY,
)
