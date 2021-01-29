package io.currency.bot.minfin.service

import io.currency.bot.minfin.config.MinfinProperties
import io.currency.bot.minfin.model.Currency
import it.skrape.core.htmlDocument
import it.skrape.extract
import it.skrape.selects.and
import it.skrape.selects.html5.button
import it.skrape.selects.html5.div
import it.skrape.selects.html5.span
import org.springframework.stereotype.Component
import it.skrape.skrape
import java.time.LocalDateTime
import java.time.LocalTime

@Component
class MinfinAuctionParser(private val props: MinfinProperties) {
    fun getCurrencyAuction(): List<Currency> {
        return skrape {
            url = props.default.preparedUrl
            extract {
                htmlDocument {
                    button {
                        withClass = "Card"
                        findAll {
                            map {
                                val rate = htmlDocument(it.html).div {
                                    withClass = "Typography" and "cardHeadlineL" and "align"
                                    findFirst { text.split(" ")[0].replace(',', '.').toDouble() }
                                }
                                val address = htmlDocument(it.html).span {
                                    withClass = "text-address"
                                    findFirst { text }
                                }
                                val details = htmlDocument(it.html).span {
                                    withClass = "Typography" and "md-6-offset"
                                    findFirst { text }
                                }
                                val time = htmlDocument(it.html).span {
                                    withClass = "Typography" and "caption2" and "align"
                                    findFirst { parseTime(text) }
                                }
                                Currency(rate = rate,
                                        time = time,
                                        address = address,
                                        details = details)
                            }.toList()
                        }
                    }
                }
            }
        }
    }

    private fun parseTime(text: String): LocalDateTime {
        val tempText = text.split(" ")
        var timeText = tempText[tempText.size - 1]
        timeText = if (timeText.length > 4) timeText else "0$timeText"

        val time = LocalTime.parse(timeText)
        return LocalDateTime.now().withHour(time.hour).withMinute(time.minute)
    }
}
