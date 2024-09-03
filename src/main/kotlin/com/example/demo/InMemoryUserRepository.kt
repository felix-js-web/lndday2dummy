package com.example.demo

import jakarta.annotation.PostConstruct
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Repository
import java.util.*

@Repository
@Profile("test")
internal class InMemoryUserRepository : UserRepository {
    private val users = mutableMapOf<Long, User>()
    private var nextId = 1L

    @PostConstruct
    fun init() {
        users[nextId] = User(nextId++, "John Doe")
        users[nextId] = User(nextId++, "Default User 2")
    }

    override fun findAll(): List<User> = users.values.toList()

    override fun save(user: User): Int {
        user.id = nextId++
        users[user.id!!] = user
        return user.id!!.toInt()
    }

//    override fun findById(id: Long): Optional<User> = Optional.ofNullable(users[id])
//
//    override fun deleteById(id: Long) {
//        users.remove(id)
//    }
}