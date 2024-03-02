package poc.taskmanager.taskmanagement.service

import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import poc.taskmanager.UnitTests
import poc.taskmanager.taskmanagement.builder.taskEntityBuilder
import poc.taskmanager.taskmanagement.exceptions.TaskNotFoundException
import poc.taskmanager.taskmanagement.repository.TaskRepository

class DeleteTaskServiceTest : UnitTests() {

    @MockK
    lateinit var repository: TaskRepository

    @InjectMockKs
    lateinit var service: DeleteTaskService

    @Test
    fun `Should delete a task when exists on database`() {
        // arrange
        val existingEntity = taskEntityBuilder()

        every { repository.existsById(existingEntity.id!!) } returns true
        every { repository.deleteById(existingEntity.id!!) } returns Unit

        // action
        service.delete(existingEntity.id!!)

        // assertion
        verify(exactly = 1) { repository.existsById(existingEntity.id!!) }
        verify(exactly = 1) { repository.deleteById(existingEntity.id!!) }
    }

    @Test
    fun `Should threw an exception when dont exists on database`() {
        // arrange
        val existingEntity = taskEntityBuilder()

        every { repository.existsById(existingEntity.id!!) } returns false

        // action
        assertThrows<TaskNotFoundException> {
            service.delete(existingEntity.id!!)
        }

        // assertion
        verify(exactly = 1) { repository.existsById(existingEntity.id!!) }
        verify(exactly = 0) { repository.deleteById(existingEntity.id!!) }
    }
}
