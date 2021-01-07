package io.currency.bot.minfin.service

import io.currency.bot.minfin.client.MinFinClient
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service

@Service
class IdleJob(private val minFinClient: MinFinClient) {
    @Scheduled(fixedDelay = 19 * 60 * 1000)
    fun execute() {
        val idleResponse = minFinClient.idleCall()
        println("Calling itself: $idleResponse")
    }
}