package com.github.kotlintelegrambot.dispatcher.handlers

import io.currency.bot.minfin.telegram.Bot
import com.github.kotlintelegrambot.entities.InlineQuery
import io.currency.bot.minfin.telegram.dispatcher.handlers.HandleInlineQuery
import io.currency.bot.minfin.telegram.dispatcher.handlers.HandleUpdate
import io.currency.bot.minfin.telegram.dispatcher.handlers.Handler
import io.currency.bot.minfin.telegram.entities.Update

data class InlineQueryHandlerEnvironment(
        val bot: Bot,
        val update: Update,
        val inlineQuery: InlineQuery
)

internal class InlineQueryHandler(
    handleInlineQuery: HandleInlineQuery
) : Handler(InlineQueryHandlerProxy(handleInlineQuery)) {
    override val groupIdentifier: String
        get() = "InlineQueryHandler"

    override fun checkUpdate(update: Update): Boolean = update.inlineQuery != null
}

private class InlineQueryHandlerProxy(
    private val handleInlineQuery: HandleInlineQuery
) : HandleUpdate {

    override fun invoke(bot: Bot, update: Update) {
        val inlineQuery = update.inlineQuery
        checkNotNull(inlineQuery)
        val inlineQueryHandlerEnv = InlineQueryHandlerEnvironment(bot, update, inlineQuery)
        handleInlineQuery(inlineQueryHandlerEnv)
    }
}
