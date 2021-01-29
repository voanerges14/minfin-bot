package io.currency.bot.minfin.service

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Disabled
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@Disabled
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
internal class UserServiceTest(@Autowired private val userService: UserService) {

    @Test
    fun getOrRegisterUser() {
    }

    @Test
    fun updateUser() {
    }

    @Test
    fun getAllUsers() {
    }

    @Test
    fun getUser() {
        userService.getUser(1)
    }
}