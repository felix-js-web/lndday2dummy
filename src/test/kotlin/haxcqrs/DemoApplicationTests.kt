package haxcqrs

import lnd.haxcqrs.DemoApplication
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles


@SpringBootTest(classes = [DemoApplication::class])
@ActiveProfiles("test")
class DemoApplicationTests {

	@Test
	fun contextLoads() {
	}

}