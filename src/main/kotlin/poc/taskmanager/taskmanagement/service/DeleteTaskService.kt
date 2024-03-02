package poc.taskmanager.taskmanagement.service

import org.springframework.stereotype.Service
import poc.taskmanager.taskmanagement.exceptions.TaskNotFoundException
import poc.taskmanager.taskmanagement.repository.TaskRepository

@Service
class DeleteTaskService(
    private val repository: TaskRepository
) {
    fun delete(id: Long) =
        if (repository.existsById(id)) repository.deleteById(id)
        else throw TaskNotFoundException()
}
