package com.github.kotlintelegrambot.dispatcher.handlers

import io.currency.bot.minfin.telegram.Bot
import io.currency.bot.minfin.telegram.dispatcher.handlers.HandleNewChatMembers
import io.currency.bot.minfin.telegram.dispatcher.handlers.HandleUpdate
import io.currency.bot.minfin.telegram.dispatcher.handlers.Handler
import io.currency.bot.minfin.telegram.entities.Message
import io.currency.bot.minfin.telegram.entities.Update
import io.currency.bot.minfin.telegram.entities.User

data class NewChatMembersHandlerEnvironment(
        val bot: Bot,
        val update: Update,
        val message: Message,
        val newChatMembers: List<User>
)

internal class NewChatMembersHandler(
    handleNewChatMembers: HandleNewChatMembers
) : Handler(NewChatMembersHandlerProxy(handleNewChatMembers)) {
    override val groupIdentifier: String
        get() = "newChatMembers"

    override fun checkUpdate(update: Update): Boolean {
        val newChatMembers = update.message?.newChatMembers
        return newChatMembers != null && newChatMembers.isNotEmpty()
    }
}

private class NewChatMembersHandlerProxy(
    val handleNewChatMembers: HandleNewChatMembers
) : HandleUpdate {
    override fun invoke(bot: Bot, update: Update) {
        val message = update.message
        val newChatMembers = message?.newChatMembers
        checkNotNull(newChatMembers)

        val newChatMembersHandlerEnv = NewChatMembersHandlerEnvironment(
            bot,
            update,
            message,
            newChatMembers
        )
        handleNewChatMembers.invoke(newChatMembersHandlerEnv)
    }
}
