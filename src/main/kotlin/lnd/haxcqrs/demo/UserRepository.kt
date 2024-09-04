package lnd.haxcqrs.demo

interface UserRepository{
    fun findAll(): List<User>
    fun save(user: User): Int
}