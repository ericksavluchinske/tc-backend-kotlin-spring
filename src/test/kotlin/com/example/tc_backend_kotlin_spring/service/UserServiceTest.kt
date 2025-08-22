package com.example.tc_backend_kotlin_spring.service

import com.example.tc_backend_kotlin_spring.entity.Role
import com.example.tc_backend_kotlin_spring.entity.User
import com.example.tc_backend_kotlin_spring.repository.RoleRepository
import com.example.tc_backend_kotlin_spring.repository.UserRepository
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.jupiter.MockitoExtension
import java.util.Optional

@ExtendWith(MockitoExtension::class)
class UserServiceTest {

    @Mock
    private lateinit var userRepository: UserRepository

    @Mock
    private lateinit var roleRepository: RoleRepository

    @InjectMocks
    private lateinit var userService: UserService

    @Test
    fun `should create and return a new role`() {

        val roleName = "ADMIN"
        val roleToSave = Role(name = roleName)
        val savedRole = roleToSave.copy(id = 1)
        `when`(roleRepository.save(roleToSave)).thenReturn(savedRole)


        val result = userService.createRole(roleName)


        assertEquals(savedRole, result)
    }

    @Test
    fun `should assign a role to a user`() {

        val user = User(id = 1, login = "testuser", url = "http://test.com")
        val role = Role(id = 1, name = "ADMIN")
        `when`(userRepository.findById(user.id)).thenReturn(Optional.of(user))
        `when`(roleRepository.findById(role.id)).thenReturn(Optional.of(role))
        `when`(userRepository.save(user)).thenReturn(user)


        val updatedUser = userService.assignRoleToUser(user.id, role.id)


        assertTrue(updatedUser.roles.contains(role))
        assertEquals(1, updatedUser.roles.size)
    }

    @Test
    fun `should get all users with their roles`() {

        val role = Role(id = 1, name = "ADMIN")
        val userWithRole = User(id = 1, login = "user1", url = "http://user1.com", roles = mutableSetOf(role))
        val users = listOf(userWithRole)
        `when`(userRepository.findAll()).thenReturn(users)


        val result = userService.getAllUsersWithRoles()


        assertEquals(1, result.size)
        assertEquals(userWithRole.login, result.first().login)
        assertEquals(1, result.first().roles.size)
        assertEquals(role.name, result.first().roles.first().name)
    }
}