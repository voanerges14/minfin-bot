package io.currency.bot.minfin.telegram.dispatcher.handlers

import io.currency.bot.minfin.telegram.Bot
import com.github.kotlintelegrambot.errors.TelegramError

data class ErrorHandlerEnvironment(
        val bot: Bot,
        val error: TelegramError
)

class ErrorHandler(private val handler: HandleError) {

    operator fun invoke(bot: Bot, error: TelegramError) {
        val errorHandlerEnvironment = ErrorHandlerEnvironment(bot, error)
        handler.invoke(errorHandlerEnvironment)
    }
}
