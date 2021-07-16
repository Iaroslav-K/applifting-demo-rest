package com.demo.applifting.repository

import com.demo.applifting.model.entity.User
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : CrudRepository<User, Long> {

    fun findByEmail(email: String): User?

    fun findByAccessToken(token: String): User?
}
