package io.currency.bot.minfin.client

import io.currency.bot.minfin.config.TelegramProperties
import khttp.post
import org.springframework.stereotype.Component

@Component
class TelegramClient (
    private val props: TelegramProperties
) {

    fun sendMessage(chatId: Long, text: String) {
        post(props.host + props.apiKey + "/sendMessage",
                data = mapOf(
                        "chat_id" to chatId,
                        "text" to text))
    }
}