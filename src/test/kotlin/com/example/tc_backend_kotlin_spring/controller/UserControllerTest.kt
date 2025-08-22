package com.example.tc_backend_kotlin_spring.controller

import com.example.tc_backend_kotlin_spring.dto.RoleRequest
import com.example.tc_backend_kotlin_spring.dto.RoleResponse
import com.example.tc_backend_kotlin_spring.dto.UserResponse
import com.example.tc_backend_kotlin_spring.entity.Role
import com.example.tc_backend_kotlin_spring.service.UserService
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WebMvcTest(UserController::class)
class UserControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockBean
    private lateinit var userService: UserService

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @Test
    fun `should create a new role and return status 201`() {

        val request = RoleRequest("ADMIN")
        val expectedRole = Role(id = 1, name = "ADMIN")
        `when`(userService.createRole(request.name)).thenReturn(expectedRole)


        mockMvc.perform(
            post("/api/roles")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request))
        )
            .andExpect(status().isCreated)
            .andExpect(jsonPath("$.name").value("ADMIN"))
    }

    @Test
    fun `should get all users with roles`() {

        val users = listOf(
            UserResponse(
                id = 1,
                login = "user1",
                url = "http://user1.com",
                roles = setOf(RoleResponse(id = 1, name = "ADMIN"))
            )
        )
        `when`(userService.getAllUsersWithRoles()).thenReturn(users)


        mockMvc.perform(get("/api/users"))
            .andExpect(status().isOk)
            .andExpect(jsonPath("$[0].login").value("user1"))
    }
}