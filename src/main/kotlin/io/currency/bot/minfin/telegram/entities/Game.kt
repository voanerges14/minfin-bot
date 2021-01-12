package io.currency.bot.minfin.telegram.entities

import com.fasterxml.jackson.databind.PropertyNamingStrategy
import com.fasterxml.jackson.databind.annotation.JsonNaming
import com.github.kotlintelegrambot.entities.files.Animation
import com.github.kotlintelegrambot.entities.files.PhotoSize
import io.currency.bot.minfin.telegram.entities.MessageEntity

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy::class)
data class Game(
        val title: String,
        val description: String,
        val photo: List<PhotoSize>,
        val text: String? = null,
        val textEntities: List<MessageEntity>? = null,
        val animation: Animation? = null
)
