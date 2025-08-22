package com.example.tc_backend_kotlin_spring.repository

import com.example.tc_backend_kotlin_spring.entity.Role
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface RoleRepository : JpaRepository<Role, Long> {
}