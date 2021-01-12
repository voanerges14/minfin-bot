package io.currency.bot.minfin.telegram.entities

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.PropertyNamingStrategy
import com.fasterxml.jackson.databind.annotation.JsonNaming


/**
 * Represents one special entity in a text message. For example, hashtags, usernames, URLs, etc.
 */
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy::class)
data class MessageEntity(
        val type: Type,
        val offset: Int,
        val length: Int,
        val url: String? = null,
        val user: User? = null,
        val language: String? = null
) {
    @JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy::class)
    enum class Type {
        @JsonProperty("mention")
        MENTION,
        @JsonProperty("hashtag")
        HASHTAG,
        @JsonProperty("cashtag")
        CASHTAG,
        @JsonProperty("bot_command")
        BOT_COMMAND,
        @JsonProperty("url")
        URL,
        @JsonProperty("email")
        EMAIL,
        @JsonProperty("phone_number")
        PHONE_NUMBER,
        @JsonProperty("bold")
        BOLD,
        @JsonProperty("italic")
        ITALIC,
        @JsonProperty("underline")
        UNDERLINE,
        @JsonProperty("strikethrough")
        STRIKETHROUGH,
        @JsonProperty("code")
        CODE,
        @JsonProperty("pre")
        PRE,
        @JsonProperty("text_link")
        TEXT_LINK,
        @JsonProperty("text_mention")
        TEXT_MENTION
    }
}
