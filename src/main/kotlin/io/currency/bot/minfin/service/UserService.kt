package io.currency.bot.minfin.service

import io.currency.bot.minfin.model.TelegramUser
import io.currency.bot.minfin.repository.UserRepository
import io.currency.bot.minfin.telegram.entities.User
import org.springframework.stereotype.Service

@Service
class UserService(
        private val userRepository: UserRepository
) {

    fun getOrRegisterUser(user: User): TelegramUser {
        return userRepository.findById(user.id)
                .orElse(userRepository.save(TelegramUser(
                        id = user.id,
                        firstName = user.firstName,
                        username = user.username)))
    }

    fun updateUser(tgUser: TelegramUser): TelegramUser {
        return userRepository.save(tgUser)
    }

    fun getAllUsers(): MutableList<TelegramUser> {
        return userRepository.findAll()
    }
}
