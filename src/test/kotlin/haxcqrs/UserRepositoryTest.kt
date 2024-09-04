package haxcqrs

import lnd.haxcqrs.DemoApplication
import lnd.haxcqrs.demo.UserRepository
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension::class)
@SpringBootTest(classes = [DemoApplication::class])
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
@ActiveProfiles("test")
class UserRepositoryTest @Autowired constructor(
        val userRepository: UserRepository
) {

    @Test
    fun `When findAll then return users`() {
        val users = userRepository.findAll()
        assertEquals(2, users.size)
        assertEquals("John Doe", users.first().name)
    }

}