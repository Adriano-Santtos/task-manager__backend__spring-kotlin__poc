package poc.taskmanager.taskmanagement.createtask

data class CreateTaskResponse(
    val title: String,
    val description: String?,
    val status: String,
)
