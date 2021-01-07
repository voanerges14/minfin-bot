package io.currency.bot.minfin

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@SpringBootApplication
class MinfinApplication

fun main(args: Array<String>) {
	runApplication<MinfinApplication>(*args)
}
