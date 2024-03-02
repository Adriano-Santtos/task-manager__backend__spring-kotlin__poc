package poc.taskmanager.taskmanagement.controller

import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Test
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import poc.taskmanager.IntegrationTests
import poc.taskmanager.taskmanagement.builder.taskEntityBuilder

class DeleteTaskControllerTest : IntegrationTests() {

    @Test
    fun `Should delete a task with success`() {

        val entity = taskEntityBuilder()
        repository.save(entity)

        mockMvc.perform(
            delete("/tasks/{id}", entity.id)
        )
            .andExpect(status().isOk)

        assertFalse(repository.existsById(entity.id!!))
    }

    @Test
    fun `Should return bad request when try delete a non existent task`() {
        val nonSavedTask = taskEntityBuilder()

        mockMvc.perform(
            delete("/tasks/{id}", nonSavedTask.id)
        )
            .andExpect(status().isBadRequest)

        assertFalse(repository.existsById(nonSavedTask.id!!))

    }
}
