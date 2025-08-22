package com.example.tc_backend_kotlin_spring.service

import com.example.tc_backend_kotlin_spring.dto.RoleResponse
import com.example.tc_backend_kotlin_spring.dto.UserResponse
import com.example.tc_backend_kotlin_spring.entity.Role
import com.example.tc_backend_kotlin_spring.entity.User
import com.example.tc_backend_kotlin_spring.repository.RoleRepository
import com.example.tc_backend_kotlin_spring.repository.UserRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserService(
    private val userRepository: UserRepository,
    private val roleRepository: RoleRepository
) {

    @Transactional
    fun createRole(name: String): Role {
        val role = Role(name = name)
        return roleRepository.save(role)
    }

    @Transactional
    fun assignRoleToUser(userId: Long, roleId: Long): User {
        val user = userRepository.findByIdOrNull(userId) ?: throw RuntimeException("User not found")
        val role = roleRepository.findByIdOrNull(roleId) ?: throw RuntimeException("Role not found")

        user.roles.add(role)
        return userRepository.save(user)
    }

    fun getAllUsersWithRoles(): List<UserResponse> {
        val users = userRepository.findAll()
        return users.map { user ->
            UserResponse(
                id = user.id,
                login = user.login,
                url = user.url,
                roles = user.roles.map { role ->
                    RoleResponse(
                        id = role.id,
                        name = role.name
                    )
                }.toSet()
            )
        }
    }
}