package io.currency.bot.minfin.service

import io.currency.bot.minfin.client.TelegramClient
import io.currency.bot.minfin.config.AuctionProperties
import io.currency.bot.minfin.model.Rate
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import kotlin.math.abs

@Service
class AuctionJob(
        private val auctionService: AuctionService,
        private val auctionProperties: AuctionProperties,
        private val telegramClient: TelegramClient) {

        companion object {
            const val WARNING_RATE_DELTA = 0.05
        }

    @Scheduled(cron = "\${auction.scheduler.cron}")
    fun execute() {
        val actualRate = auctionService.getCurrentAverageRate()
        val lowestRate = auctionService.getDayLowestAverageRate() ?: auctionService.updateRate(actualRate, Rate())

        println("Actual rate: ${actualRate.avg}")
        println("Lowest rate: ${lowestRate.avg}")

        val diff = actualRate.avg - lowestRate.avg
        if (abs(diff) >= auctionProperties.rateDelta) {
            val text = String.format("Min day rate: %.2f. Current: %.2f (%.3f)", lowestRate.avg, actualRate.avg, diff)
            println(text)
            telegramClient.notify(text)
        }

        if (lowestRate.avg > actualRate.avg) {
            println("Saving to db")
            auctionService.updateRate(actualRate, lowestRate)
        }
    }
}
