package poc.taskmanager

import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.BeforeEach
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MockMvc
import poc.taskmanager.taskmanagement.repository.TaskRepository

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = RANDOM_PORT, classes = [TaskManagerApplication::class])
@ActiveProfiles("test")
class IntegrationTests {

    @Autowired
    lateinit var mockMvc: MockMvc

    @Autowired
    lateinit var objectMapper: ObjectMapper

    @Autowired
    lateinit var repository: TaskRepository

    @BeforeEach
    fun beforeEach() {
        repository.deleteAll()
    }
}
