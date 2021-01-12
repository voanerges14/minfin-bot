package io.currency.bot.minfin.service

import io.currency.bot.minfin.client.TelegramClient
import io.currency.bot.minfin.model.TelegramUser
import io.currency.bot.minfin.telegram.entities.Message
import io.currency.bot.minfin.telegram.entities.Update
import org.springframework.stereotype.Service

@Service
class TelegramWebhookService(
        private val telegramClient: TelegramClient,
        private val auction: AuctionJob,
        private val userService: UserService
) {

    companion object {
        const val START_COMMAND = "/start"
        const val ECHO_COMMAND = "/echo"
        const val SET_RATE_COMMAND = "/rate"
    }

    fun onUpdate(update: Update) {
        if (update.message != null) {
            val chatId = update.message.chat.id
            val text = update.message.text

            val tgUser = userService.getOrRegisterUser(update.message.from)

            when {
                text?.startsWith(START_COMMAND) == true -> onStartCommand(update.message)
                text?.startsWith(ECHO_COMMAND) == true -> onEchoCommand(chatId, text)
                text?.startsWith(SET_RATE_COMMAND) == true -> onSetRate(tgUser, text)
            }
        }
    }

    private fun onStartCommand(message: Message) {
        telegramClient.sendMessage(message.chat.id, "Hello! I'm Minfin Bot.")
    }

    private fun onEchoCommand(chatId: Long, text: String) {
        val response = text.subSequence(ECHO_COMMAND.length, text.length).trim().toString()
        telegramClient.sendMessage(chatId, response)
    }

    private fun onSetRate(tgUser: TelegramUser, text: String) {
        val rate = text
                .subSequence(SET_RATE_COMMAND.length, text.length).toString()
                .replace("\uFEFF", "")
                .toDoubleOrNull()
        tgUser.baseRate = rate
        auction.executeCheck(userService.updateUser(tgUser))
    }
}
