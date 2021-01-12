package com.github.kotlintelegrambot

import io.currency.bot.minfin.telegram.entities.Update
import com.google.gson.Gson

internal class UpdateMapper(private val gson: Gson) {

    fun jsonToUpdate(updateJson: String): Update = gson.fromJson(updateJson, Update::class.java)
}
