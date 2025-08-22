package com.example.tc_backend_kotlin_spring

import com.example.tc_backend_kotlin_spring.service.GitHubUserService
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@SpringBootApplication
@Configuration
class TcBackendKotlinSpringApplication{

	@Bean
	fun run(gitHubUserService: GitHubUserService): CommandLineRunner {
		return CommandLineRunner {
			gitHubUserService.syncGitHubUsers()
		}
	}
}

fun main(args: Array<String>) {
	runApplication<TcBackendKotlinSpringApplication>(*args)
}