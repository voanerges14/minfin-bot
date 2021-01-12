package io.currency.bot.minfin.controller

import io.currency.bot.minfin.service.TelegramWebhookService
import io.currency.bot.minfin.telegram.entities.Update
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import java.util.logging.Logger

@RestController
class TelegramWebhookController(private val telegramWebhookService: TelegramWebhookService) {

    private val logger: Logger = Logger.getLogger("[MinFin-Bot]")

    @PostMapping("/\${telegram.apiKey}")
    fun onUpdate(@RequestBody update: Update) {
        telegramWebhookService.onUpdate(update)
        logger.info("Got updates from Telegram: $update")
    }
}