package com.demo.applifting.model.entity

import javax.persistence.*

/**
 * @param accessToken UUID
 */
@Entity
@Table(name = "USER_TABLE")
data class User(
    @Id
    @GeneratedValue
    val userId: Long?,
    val userName: String,
    @Column(unique = true)
    val email: String,
    @Column(unique = true)
    val accessToken: String,
)
