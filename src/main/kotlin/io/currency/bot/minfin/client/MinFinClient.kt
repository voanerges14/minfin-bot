package io.currency.bot.minfin.client

import io.currency.bot.minfin.config.MinfinProperties
import org.springframework.stereotype.Component
import khttp.get

@Component
class MinFinClient(
        private val properties: MinfinProperties,
){
    fun getMinfinCurrencies(): String {
        return get(properties.url).text
    }

    fun idleCall(): String {
        return get("https://bot-minfin.herokuapp.com/").text
    }
}