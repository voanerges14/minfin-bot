package io.currency.bot.minfin.telegram.dispatcher.handlers

import io.currency.bot.minfin.telegram.dispatcher.handlers.HandleUpdate
import io.currency.bot.minfin.telegram.entities.Update

abstract class Handler(val handlerCallback: HandleUpdate) {
    abstract val groupIdentifier: String

    abstract fun checkUpdate(update: Update): Boolean
}
