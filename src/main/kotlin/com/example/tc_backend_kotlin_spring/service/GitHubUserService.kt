package com.example.tc_backend_kotlin_spring.service

import com.example.tc_backend_kotlin_spring.entity.User
import com.example.tc_backend_kotlin_spring.repository.UserRepository
import com.example.tc_backend_kotlin_spring.service.dto.GitHubUserResponse
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate

@Service
class GitHubUserService(
    private val userRepository: UserRepository
) {

    private val gitHubApiUrl = "https://api.github.com/users?per_page=30"
    private val restTemplate = RestTemplate()

    fun syncGitHubUsers() {

        val usersFromApi: Array<GitHubUserResponse>? = restTemplate.getForObject(gitHubApiUrl, Array<GitHubUserResponse>::class.java)

        usersFromApi?.forEach { apiUser ->
            val user = User(
                login = apiUser.login,
                url = apiUser.url
            )
            userRepository.save(user)
        }
    }
}