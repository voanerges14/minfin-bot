package io.currency.bot.minfin.service

import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
internal class MinfinAuctionParserTest(@Autowired private val minfinAuctionParser: MinfinAuctionParser) {

    @Test
    @Disabled
    fun getCurrencyAuction() {
        val n = minfinAuctionParser.getCurrencyAuction()
        println(n)
    }
}