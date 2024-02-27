package poc.taskmanager.taskmanagement.response

data class TaskResponse(
    val title: String,
    val description: String?,
    val status: String,
)
