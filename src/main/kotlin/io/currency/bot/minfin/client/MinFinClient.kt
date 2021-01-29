package io.currency.bot.minfin.client

import org.springframework.stereotype.Component
import khttp.get

@Component
class MinFinClient(
){
    fun idleCall(): String {
        return get("https://bot-minfin.herokuapp.com/").text
    }
}