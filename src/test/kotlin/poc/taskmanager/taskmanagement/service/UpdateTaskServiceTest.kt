package poc.taskmanager.taskmanagement.service

import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import poc.taskmanager.UnitTests
import poc.taskmanager.taskmanagement.dto.UpdateTaskDTO
import poc.taskmanager.taskmanagement.entities.TaskEntity
import poc.taskmanager.taskmanagement.repository.TaskRepository

class UpdateTaskServiceTest : UnitTests() {
    @MockK
    lateinit var repository: TaskRepository

    @InjectMockKs
    lateinit var service: UpdateTaskService

    @Test
    fun `Should update existing task with non-null properties`() {
        // Arrange
        val taskId = 1L
        val updateTaskDTO = UpdateTaskDTO(title = "New Title", description = "New Description")
        val existingEntity = TaskEntity(
            id = taskId,
            title = "Old Title",
            description = "Old Description",
            status = "TODO"
        )

        every { repository.getReferenceById(taskId) } returns existingEntity
        every { repository.save(any()) } returns existingEntity

        // Act
        service.update(taskId, updateTaskDTO)

        // Assert
        verify(exactly = 1) { repository.getReferenceById(taskId) }
        verify(exactly = 1) { repository.save(existingEntity) }

        assertEquals("New Title", existingEntity.title)
        assertEquals("New Description", existingEntity.description)
        assertEquals("TODO", existingEntity.status)
    }

    @Test
    fun `Should update existing task with not null new properties`() {
        // Arrange
        val taskId = 1L
        val updateTaskDTO = UpdateTaskDTO(title = "New Title", description = null)
        val existingEntity = TaskEntity(
            id = taskId,
            title = "Old Title",
            description = "Old Description",
            status = "TODO"
        )

        every { repository.getReferenceById(taskId) } returns existingEntity
        every { repository.save(any()) } returns existingEntity

        // Act
        service.update(taskId, updateTaskDTO)

        // Assert
        verify(exactly = 1) { repository.getReferenceById(taskId) }
        verify(exactly = 1) { repository.save(existingEntity) }

        assertEquals("New Title", existingEntity.title)
        assertEquals("Old Description", existingEntity.description)
        assertEquals("TODO", existingEntity.status)
    }
}
