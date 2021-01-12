package io.currency.bot.minfin.repository

import io.currency.bot.minfin.model.TelegramUser
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository: JpaRepository<TelegramUser, Long> {}
