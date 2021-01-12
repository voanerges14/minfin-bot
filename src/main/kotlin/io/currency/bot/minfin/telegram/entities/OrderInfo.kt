package io.currency.bot.minfin.telegram.entities

import com.fasterxml.jackson.databind.PropertyNamingStrategy
import com.fasterxml.jackson.databind.annotation.JsonNaming
import com.github.kotlintelegrambot.entities.ShippingAddress

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy::class)
data class OrderInfo(
        val name: String? = null,
        val phoneNumber: String? = null,
        val email: String? = null,
        val shippingAddress: ShippingAddress? = null
)
