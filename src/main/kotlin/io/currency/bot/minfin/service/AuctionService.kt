package io.currency.bot.minfin.service

import io.currency.bot.minfin.model.Rate
import io.currency.bot.minfin.repository.AuctionRepository
import io.currency.bot.minfin.repository.RateRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AuctionService(
        private val currency: MinfinAuctionParser,
        private val repository: AuctionRepository,
        private val rateRepository: RateRepository
) {

    fun getRateDelta(): Double {
        return getCurrentAverageRate().avg - (getPreviousAverageRate()?.avg ?: 0.0)
    }

    fun getCurrentAverageRate(): Rate {
        return Rate(avg = currency.getCurrencyAuction().map { c -> c.rate }.average())
    }

    fun getPreviousAverageRate(): Rate? {
        return rateRepository.findTopByOrderByCreatedAtDesc()
    }

    fun getDayLowestAverageRate(): Rate? {
        return rateRepository.findTopByOrderByAvgAsc()
    }

    fun addRate(rate: Rate): Rate {
        return rateRepository.save(rate)
    }

    @Transactional
    fun updateRate(rateC: Rate, rateL: Rate): Rate {
        rateRepository.findById(rateL.id).map { rateRepository::delete }
        return rateRepository.save(rateC)
    }
}
