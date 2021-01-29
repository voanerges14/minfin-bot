package io.currency.bot.minfin.service

import io.currency.bot.minfin.config.MinfinProperties
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
internal class MinfinAuctionParserTest(
        @Autowired private val minfinAuctionParser: MinfinAuctionParser,
        @Autowired private val minfinProperties: MinfinProperties,
) {

    @Test
    @Disabled
    fun getCurrencyAuction() {
        minfinProperties.default.city = "k"
        minfinProperties.default.currency = "kf"
        val n = minfinAuctionParser.getCurrencyAuction()
        println(n)
    }
}