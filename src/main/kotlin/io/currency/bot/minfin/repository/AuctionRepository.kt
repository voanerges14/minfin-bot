package io.currency.bot.minfin.repository

import io.currency.bot.minfin.model.Currency
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AuctionRepository: JpaRepository<Currency, Long> {
    fun findTopByOrderByCreatedAtDesc(): Currency?
}