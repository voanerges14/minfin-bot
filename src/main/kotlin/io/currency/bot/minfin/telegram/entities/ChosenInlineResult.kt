package io.currency.bot.minfin.telegram.entities

import com.github.kotlintelegrambot.entities.Location
import com.google.gson.annotations.SerializedName as Name

data class ChosenInlineResult(
        @Name("result_id") val resultId: String,
        val from: User,
        val location: Location? = null,
        @Name("inline_message_id") val inlineMessageId: String? = null,
        val query: String
)
