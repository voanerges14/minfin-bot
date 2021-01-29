package io.currency.bot.minfin.service

import io.currency.bot.minfin.client.TelegramClient
import io.currency.bot.minfin.config.AuctionProperties
import io.currency.bot.minfin.config.MinfinProperties
import io.currency.bot.minfin.model.Rate
import io.currency.bot.minfin.model.TelegramUser
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import java.util.logging.Logger
import kotlin.math.abs
import kotlin.math.roundToInt

@Service
class AuctionJob(
        private val auctionService: AuctionService,
        private val userService: UserService,
        private val auctionProperties: AuctionProperties,
        private val telegramClient: TelegramClient,
        private val minfinProperties: MinfinProperties) {

    private val logger: Logger = Logger.getLogger("[MinFin-Bot]")

    @Scheduled(cron = "\${auction.scheduler.cron}")
    fun execute() {

        val actualRates = getCurrenciesRate()

        val actualRate = actualRates["usd"]!!
        val lowestRate = auctionService.getDayLowestAverageRate() ?: auctionService.updateRate(actualRate, Rate())

        logger.info("Actual rate: ${actualRate.avg}")
        logger.info("Lowest rate: ${lowestRate.avg}")

        userService.getAllUsers().forEach { doUpdateAndNotify(getUserRate(lowestRate.avg, it, actualRates), lowestRate.avg, it) }

        if (lowestRate.avg > actualRate.avg) {
            logger.info("Saving to db")
            auctionService.updateRate(actualRate, lowestRate)
        }
    }

    fun executeCheck(tgUser: TelegramUser) {
        val actualRate = auctionService.getCurrentAverageRate(tgUser.currency, tgUser.city)
        val lowestRate = auctionService.getDayLowestAverageRate() ?: auctionService.updateRate(actualRate, Rate())

        doUpdateAndNotify(actualRate.avg, lowestRate.avg, tgUser)
    }

    private fun getUserRate(lowestRate: Double, tgUser: TelegramUser, rates: Map<String, Rate>) : Double {
        return rates[tgUser.currency]?.avg ?: lowestRate;
    }

    private fun getCurrenciesRate() : Map<String, Rate> {
       return minfinProperties.currencies.map { it to auctionService.getCurrentAverageRate(it, null)}.toMap()
    }

    private fun doUpdateAndNotify(minfinRate: Double, lowestRate: Double, tgUser: TelegramUser) {
        val userRate = tgUser.baseRate ?: lowestRate
        val delta = tgUser.delta ?: auctionProperties.rateDelta

        val diff = ((minfinRate - userRate) * 100.0).roundToInt() / 100.0
        val lastDiff = tgUser.diff ?: 0

        if (lastDiff != diff && abs(diff) >= delta) {
            val text = String.format("Base rate: %.2f. Current: %.2f (%.2f)", userRate, minfinRate, diff)
            logger.info("Notification text: $text")

            tgUser.diff = diff
            userService.updateUser(tgUser)
            telegramClient.sendMessage(tgUser.id, text)
        }
    }
}
