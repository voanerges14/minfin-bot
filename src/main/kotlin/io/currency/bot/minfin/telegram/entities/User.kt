package io.currency.bot.minfin.telegram.entities

import com.fasterxml.jackson.databind.PropertyNamingStrategy
import com.fasterxml.jackson.databind.annotation.JsonNaming

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy::class)
data class User(
        val id: Long,
        val isBot: Boolean,
        val firstName: String,
        val lastName: String? = null,
        val username: String? = null,
        val languageCode: String? = null,
        val canJoinGroups: Boolean? = null,
        val canReadAllGroupMessages: Boolean? = null,
        val supportsInlineQueries: Boolean? = null
)
