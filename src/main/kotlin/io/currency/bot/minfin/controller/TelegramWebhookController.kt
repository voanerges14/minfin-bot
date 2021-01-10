package io.currency.bot.minfin.controller

import com.github.kotlintelegrambot.entities.Update
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class TelegramWebhookController {

    @PostMapping("/webhook")
    fun getUpdates(@RequestBody update: Update) {
        println("Got updates from Telegram: $update")
    }

}