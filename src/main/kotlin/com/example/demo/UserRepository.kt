package com.example.demo

interface UserRepository{
    fun findAll(): List<User>
    fun save(user: User): Int
}