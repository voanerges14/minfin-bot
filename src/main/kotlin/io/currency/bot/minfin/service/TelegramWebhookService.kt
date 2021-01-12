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
        const val CITY_COMMAND = "/city"
    }

    fun onUpdate(update: Update) {
        if (update.message != null) {
            val chatId = update.message.chat.id
            val text = update.message.text

            val tgUser = userService.getOrRegisterUser(update.message.from)

            when {
                text?.startsWith(START_COMMAND) == true -> onStartCommand(chatId)
                text?.startsWith(ECHO_COMMAND) == true -> onEchoCommand(chatId, text)

                text?.startsWith(SET_RATE_COMMAND) == true -> onSetRateCommand(tgUser, text)
                text?.startsWith(CITY_COMMAND) == true -> onCityCommand(tgUser, text)
            }
        }
    }

    private fun onStartCommand(chatId: Long) {
        telegramClient.sendMessage(chatId, "Hello! I'm Minfin Bot.")
    }

    private fun onEchoCommand(chatId: Long, text: String) {
        telegramClient.sendMessage(chatId, prepareText(text, ECHO_COMMAND))
    }

    private fun onCityCommand(tgUser: TelegramUser, text: String) {
        tgUser.city = parseCityOrDefault(prepareText(text, CITY_COMMAND))
        tgUser.diff = 0.0
        auction.executeCheck(userService.updateUser(tgUser))
    }

    private fun onSetRateCommand(tgUser: TelegramUser, text: String) {
        val rate = text
                .subSequence(SET_RATE_COMMAND.length, text.length).toString()
                .replace("\uFEFF", "")
                .toDoubleOrNull()
        tgUser.baseRate = rate
        auction.executeCheck(userService.updateUser(tgUser))
    }

    private fun prepareText(text: String, command: String): String {
        return text.subSequence(command.length, text.length).trim().toString()
    }

    private fun parseCityOrDefault(city: String): String {
        if (city.contains("lvov").or(city.contains("lv")).or(city.contains("lviv"))) {
            return "lvov"
        }
        if (city.contains("kiev").or(city.contains("kv")).or(city.contains("kyiv"))) {
            return "kiev"
        }
        return "lvov"
    }
}
