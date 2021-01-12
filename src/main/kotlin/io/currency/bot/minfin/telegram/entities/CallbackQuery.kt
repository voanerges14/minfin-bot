package io.currency.bot.minfin.telegram.entities

import com.google.gson.annotations.SerializedName
import io.currency.bot.minfin.telegram.entities.Message
import io.currency.bot.minfin.telegram.entities.User

data class CallbackQuery(
        val id: String,
        val from: User,
        val message: Message? = null,
        @SerializedName("inline_message_id") val inlineMessageId: String? = null,
        val data: String,
        @SerializedName("chat_instance") val chatInstance: String
)
