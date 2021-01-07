package io.currency.bot.minfin.repository

import io.currency.bot.minfin.model.Rate
import org.springframework.data.jpa.repository.JpaRepository

interface RateRepository: JpaRepository<Rate, String> {
    fun findTopByOrderByCreatedAtDesc(): Rate?

    fun findTopByOrderByAvgAsc(): Rate?

//    findAllByStartDateLessThanEqualAndEndDateGreaterThanEqual
}
