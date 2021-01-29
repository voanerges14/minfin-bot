package io.currency.bot.minfin.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

@Component
@ConfigurationProperties(prefix = "minfin")
data class MinfinProperties(
        var currencies: List<String> = listOf("usd"),
        var default: Default = Default()
) {

    data class Default (var url: String = "",
                        var city: String = "lvov",
                        var currency: String = "usd") {
        val preparedUrl
            get() = url.plus("/ua/currency/auction/$currency/sell/$city/?order=newest")
    }
}
