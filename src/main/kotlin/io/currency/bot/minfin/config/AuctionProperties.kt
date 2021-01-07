package io.currency.bot.minfin.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

@Component
@ConfigurationProperties(prefix = "auction")
data class AuctionProperties(
        val scheduler: Scheduler = Scheduler(""),
        val rateDelta: Double = 0.05
) {
    data class Scheduler(
            var delay: String,
    )
}

