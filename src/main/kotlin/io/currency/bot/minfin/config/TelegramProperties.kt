package io.currency.bot.minfin.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

@Component
@ConfigurationProperties(prefix = "telegram")
data class TelegramProperties(
        var url: String = ""
)