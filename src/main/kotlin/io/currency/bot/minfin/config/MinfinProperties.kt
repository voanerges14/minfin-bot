package io.currency.bot.minfin.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

@Component
@ConfigurationProperties(prefix = "minfin")
data class MinfinProperties(
    var url: String = "",
    var city: String = "lvov",
    var currency: String = "USD"
)
