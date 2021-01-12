package io.currency.bot.minfin.service

import io.currency.bot.minfin.client.TelegramClient
import io.currency.bot.minfin.config.AuctionProperties
import io.currency.bot.minfin.config.TelegramProperties
import io.currency.bot.minfin.model.Rate
import io.currency.bot.minfin.model.TelegramUser
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import java.util.logging.Logger
import kotlin.math.abs

@Service
class AuctionJob(
        private val auctionService: AuctionService,
        private val userService: UserService,
        private val auctionProperties: AuctionProperties,
        private val telegramProperties: TelegramProperties,
        private val telegramClient: TelegramClient) {

    private val logger: Logger = Logger.getLogger("[MinFin-Bot]")

    @Scheduled(cron = "\${auction.scheduler.cron}")
    fun execute() {
        val actualRate = auctionService.getCurrentAverageRate()
        val lowestRate = auctionService.getDayLowestAverageRate() ?: auctionService.updateRate(actualRate, Rate())

        logger.info("Actual rate: ${actualRate.avg}")
        logger.info("Lowest rate: ${lowestRate.avg}")

        userService.getAllUsers().forEach { doUpdateAndNotify(actualRate.avg, lowestRate.avg, it) }

        if (lowestRate.avg > actualRate.avg) {
            logger.info("Saving to db")
            auctionService.updateRate(actualRate, lowestRate)
        }
    }

    fun executeCheck(tgUser: TelegramUser) {
        val actualRate = auctionService.getCurrentAverageRate()
        val lowestRate = auctionService.getDayLowestAverageRate() ?: auctionService.updateRate(actualRate, Rate())

        doUpdateAndNotify(actualRate.avg, lowestRate.avg, tgUser)
    }

    private fun doUpdateAndNotify(minfinRate: Double, lowestRate: Double, tgUser: TelegramUser) {
        val userRate = tgUser.baseRate ?: lowestRate
        val delta = tgUser.delta ?: auctionProperties.rateDelta

        val diff = minfinRate - userRate
        val lastDiff = tgUser.diff ?: 0

        if (lastDiff != diff && abs(diff) >= delta) {
            val text = String.format("Base rate: %.2f. Current: %.2f (%.2f)", userRate, minfinRate, diff)
            logger.info("Notification text: $text")

            tgUser.diff = diff
            userService.updateUser(tgUser)

            telegramClient.sendMessage(telegramProperties.channel.toLong(), text)
        }
    }
}
