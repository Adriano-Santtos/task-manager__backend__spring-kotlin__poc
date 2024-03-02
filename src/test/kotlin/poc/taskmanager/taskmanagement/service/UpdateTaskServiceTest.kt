package poc.taskmanager.taskmanagement.service

import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import poc.taskmanager.UnitTests
import poc.taskmanager.taskmanagement.builder.taskEntityBuilder
import poc.taskmanager.taskmanagement.dto.UpdateTaskDTO
import poc.taskmanager.taskmanagement.enums.TaskStatusEnum.COMPLETED
import poc.taskmanager.taskmanagement.enums.TaskStatusEnum.TODO
import poc.taskmanager.taskmanagement.exceptions.StatusNotAllowedException
import poc.taskmanager.taskmanagement.repository.TaskRepository

class UpdateTaskServiceTest : UnitTests() {
    @MockK
    lateinit var repository: TaskRepository

    @InjectMockKs
    lateinit var service: UpdateTaskService

    @Test
    fun `Should update existing task with non-null propereties`() {
        // Arrange
        val updateTaskDTO = UpdateTaskDTO(
            title = "New Title",
            description = "New Description",
            status = TODO.name,
        )
        val existingEntity = taskEntityBuilder()

        every { repository.getReferenceById(existingEntity.id!!) } returns existingEntity
        every { repository.save(any()) } returns existingEntity

        // Act
        service.update(existingEntity.id!!, updateTaskDTO)

        // Assert
        verify(exactly = 1) { repository.getReferenceById(existingEntity.id!!) }
        verify(exactly = 1) { repository.save(existingEntity) }

        assertEquals("New Title", existingEntity.title)
        assertEquals("New Description", existingEntity.description)
        assertEquals(TODO.name, existingEntity.status)
    }

    @Test
    fun `Should update existing task with not null new properties`() {
        // Arrange
        val updateTaskDTO = UpdateTaskDTO(
            title = "New Title",
            description = null,
            status = TODO.name,
        )
        val existingEntity = taskEntityBuilder()

        every { repository.getReferenceById(existingEntity.id!!) } returns existingEntity
        every { repository.save(any()) } returns existingEntity

        // Act
        service.update(existingEntity.id!!, updateTaskDTO)

        // Assert
        verify(exactly = 1) { repository.getReferenceById(existingEntity.id!!) }
        verify(exactly = 1) { repository.save(existingEntity) }

        assertEquals("New Title", existingEntity.title)
        assertEquals("Old Description", existingEntity.description)
        assertEquals(TODO.name, existingEntity.status)
    }

    @Test
    fun `Should update status with success`() {
        val updateTaskDTO = UpdateTaskDTO(
            title = "New Title",
            description = null,
            status = COMPLETED.name,
        )
        val existingEntity = taskEntityBuilder()

        every { repository.getReferenceById(existingEntity.id!!) } returns existingEntity
        every { repository.save(any()) } returns existingEntity

        // Act
        service.update(existingEntity.id!!, updateTaskDTO)

        // Assert
        verify(exactly = 1) { repository.getReferenceById(existingEntity.id!!) }
        verify(exactly = 1) { repository.save(existingEntity) }

        assertEquals("New Title", existingEntity.title)
        assertEquals("Old Description", existingEntity.description)
        assertEquals(COMPLETED.name, existingEntity.status)
    }

    @Test
    fun `Should throw an exception when status not allowed to be updated`() {
        val notAllowedStatus = "TODOS"
        val updateTaskDTO = UpdateTaskDTO(
            title = "New Title",
            description = null,
            status = notAllowedStatus,
        )
        val existingEntity = taskEntityBuilder()

        every { repository.getReferenceById(existingEntity.id!!) } returns existingEntity

        // Assert
        assertThrows<StatusNotAllowedException> {
            service.update(
                existingEntity.id!!,
                updateTaskDTO
            )
        }
        verify(exactly = 1) { repository.getReferenceById(existingEntity.id!!) }
        verify(exactly = 0) { repository.save(existingEntity) }
    }
}
