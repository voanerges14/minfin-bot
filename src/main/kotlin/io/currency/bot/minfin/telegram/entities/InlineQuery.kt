package com.github.kotlintelegrambot.entities

import io.currency.bot.minfin.telegram.entities.User

data class InlineQuery(
        val id: String,
        val from: User,
        val location: Location? = null,
        val query: String,
        val offset: String
)
