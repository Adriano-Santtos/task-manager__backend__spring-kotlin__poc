package poc.taskmanager.taskmanagement.request

data class CreateTaskRequest(
    val title: String,
    val description: String?,
)
