package com.example.tc_backend_kotlin_spring.dto

data class UserResponse(
    val id: Long,
    val login: String,
    val url: String,
    val roles: Set<RoleResponse>
)