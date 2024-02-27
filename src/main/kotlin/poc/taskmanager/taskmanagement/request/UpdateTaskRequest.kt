package poc.taskmanager.taskmanagement.request

data class UpdateTaskRequest(
    val title: String?,
    val description: String?,
    val status: String?,
)
