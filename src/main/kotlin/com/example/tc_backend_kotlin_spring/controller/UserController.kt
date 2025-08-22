package com.example.tc_backend_kotlin_spring.controller

import com.example.tc_backend_kotlin_spring.dto.RoleRequest
import com.example.tc_backend_kotlin_spring.dto.UserResponse
import com.example.tc_backend_kotlin_spring.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class UserController(
    private val userService: UserService
) {

    @PostMapping("/roles")
    fun createRole(@RequestBody request: RoleRequest): ResponseEntity<Any> {
        val createdRole = userService.createRole(request.name)
        return ResponseEntity.status(HttpStatus.CREATED).body(createdRole)
    }

    @PostMapping("/users/{userId}/roles/{roleId}")
    fun assignRoleToUser(
        @PathVariable userId: Long,
        @PathVariable roleId: Long
    ): ResponseEntity<Any> {
        return try {
            val userWithNewRole = userService.assignRoleToUser(userId, roleId)
            ResponseEntity.ok(userWithNewRole)
        } catch (e: RuntimeException) {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.message)
        }
    }

    @GetMapping("/users")
    fun getAllUsersWithRoles(): ResponseEntity<List<UserResponse>> {
        val users = userService.getAllUsersWithRoles()
        return ResponseEntity.ok(users)
    }
}