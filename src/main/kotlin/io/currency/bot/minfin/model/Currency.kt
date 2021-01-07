package io.currency.bot.minfin.model

import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.Id

@Entity
data class Currency(

        val rate: Double = 0.0,
        val time: LocalDateTime = LocalDateTime.now(),
        val address: String = "",
        val details: String = "",
        val sellerType: String = "",
        val maps: String = "",

        val createdAt: LocalDateTime = LocalDateTime.now()
) {
    @Id
    val id: String = ""
}