package io.currency.bot.minfin.controller

import io.currency.bot.minfin.model.TelegramUpdate
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class TelegramWebhookController {

    @PostMapping("/webhook")
    fun getUpdates(@RequestBody updates: TelegramUpdate) {
        println("Got updates from Telegram: $updates")
    }

}