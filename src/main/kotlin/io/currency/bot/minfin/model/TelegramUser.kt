package io.currency.bot.minfin.model

import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.Id

@Entity
data class TelegramUser(
        @Id
        val id: Long = -1,
        val firstName: String = "",
        val lastName: String? = null,
        val username: String? = null,

        var baseRate: Double? = null,
        var delta: Double? = null,
        var diff: Double? = null,

        var city: String? = null,

        val createdAt: LocalDateTime = LocalDateTime.now()
)
