package lnd.haxcqrs.demo

import org.springframework.context.annotation.Profile
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.RowMapper
import org.springframework.stereotype.Repository

@Repository
@Profile("default")
class UserRepositoryImpl(private val jdbcTemplate: JdbcTemplate) : UserRepository {

    private val rowMapper = RowMapper<User> { rs, _ ->
        User(
                id = rs.getLong("id"),
                name = rs.getString("name")
        )
    }

    override fun findAll(): List<User> =
            jdbcTemplate.query("SELECT * FROM users", rowMapper)

    override fun save(user: User): Int =
            jdbcTemplate.update("INSERT INTO users (name) VALUES (?)", user.name)
}