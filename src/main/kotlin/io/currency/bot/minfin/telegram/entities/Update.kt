package io.currency.bot.minfin.telegram.entities

import com.fasterxml.jackson.databind.PropertyNamingStrategy
import com.fasterxml.jackson.databind.annotation.JsonNaming
import com.github.kotlintelegrambot.entities.InlineQuery
import com.github.kotlintelegrambot.entities.payments.PreCheckoutQuery
import com.github.kotlintelegrambot.entities.payments.ShippingQuery
import io.currency.bot.minfin.telegram.entities.polls.Poll
import io.currency.bot.minfin.telegram.entities.polls.PollAnswer
import com.github.kotlintelegrambot.types.DispatchableObject

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy::class)
data class Update(
        val updateId: Long,
        val message: Message? = null,
        val editedMessage: Message? = null,
        val channelPost: Message? = null,
        val editedChannelPost: Message? = null,
        val inlineQuery: InlineQuery? = null,
        val chosenInlineResult: ChosenInlineResult? = null,
        val callbackQuery: CallbackQuery? = null,
        val shippingQuery: ShippingQuery? = null,
        val preCheckoutQuery: PreCheckoutQuery? = null,
        val poll: Poll? = null,
        val pollAnswer: PollAnswer? = null
) : DispatchableObject

/**
 * Generate list of key-value from start payload.
 * For more info {@link https://core.telegram.org/bots#deep-linking}
 */
fun Update.getStartPayload(delimiter: String = "-"): List<Pair<String, String>> {
    return message?.let {
        val parameters = it.text?.substringAfter("start ", "")
        if (parameters == null || parameters.isEmpty()) {
            return emptyList()
        }

        val split = parameters.split("&")
        split.map {
            val keyValue = it.split(delimiter)
            Pair(keyValue[0], keyValue[1])
        }
    } ?: emptyList()
}
