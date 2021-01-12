package com.github.kotlintelegrambot.dispatcher.handlers

import io.currency.bot.minfin.telegram.Bot
import io.currency.bot.minfin.telegram.dispatcher.handlers.HandleText
import io.currency.bot.minfin.telegram.dispatcher.handlers.HandleUpdate
import io.currency.bot.minfin.telegram.dispatcher.handlers.Handler
import io.currency.bot.minfin.telegram.entities.Message
import io.currency.bot.minfin.telegram.entities.Update

data class TextHandlerEnvironment(
        val bot: Bot,
        val update: Update,
        val message: Message,
        val text: String
)

internal class TextHandler(
    private val text: String? = null,
    handleText: HandleText
) : Handler(TextHandlerProxy(handleText)) {
    override val groupIdentifier: String = "CommandHandler"

    override fun checkUpdate(update: Update): Boolean {
        if (update.message?.text != null && text == null) return true
        else if (text != null) {
            return (update.message?.text != null && update.message.text.toLowerCase().contains(text.toLowerCase()))
        }
        return false
    }
}

private class TextHandlerProxy(
    private val handler: TextHandlerEnvironment.() -> Unit
) : HandleUpdate {

    override fun invoke(bot: Bot, update: Update) {
        checkNotNull(update.message)
        checkNotNull(update.message.text)
        val textHandlerEnv = TextHandlerEnvironment(
            bot,
            update,
            update.message,
            update.message.text
        )
        handler.invoke(textHandlerEnv)
    }
}
