package io.currency.bot.minfin.client

import io.currency.bot.minfin.config.TelegramProperties
import khttp.get
import org.springframework.stereotype.Component

@Component
class TelegramClient (
    private val props: TelegramProperties
) {
    fun notify(text: String): String {
        return get(props.url + text).text
    }
}