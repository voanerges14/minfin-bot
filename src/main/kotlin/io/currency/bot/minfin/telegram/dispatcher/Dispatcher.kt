package io.currency.bot.minfin.telegram.dispatcher

import io.currency.bot.minfin.telegram.Bot
import io.currency.bot.minfin.telegram.dispatcher.handlers.ErrorHandler
import io.currency.bot.minfin.telegram.dispatcher.handlers.Handler
import io.currency.bot.minfin.telegram.entities.Update
import com.github.kotlintelegrambot.errors.TelegramError
import com.github.kotlintelegrambot.logging.LogLevel
import com.github.kotlintelegrambot.types.DispatchableObject
import java.util.concurrent.BlockingQueue
import java.util.concurrent.LinkedBlockingQueue

class Dispatcher(
    val updatesQueue: BlockingQueue<DispatchableObject> = LinkedBlockingQueue()
) {
    internal lateinit var logLevel: LogLevel
    lateinit var bot: Bot

    private val commandHandlers = mutableMapOf<String, ArrayList<Handler>>()
    private val errorHandlers = arrayListOf<ErrorHandler>()
    private var stopped = false

    fun startCheckingUpdates() {
        stopped = false
        checkQueueUpdates()
    }

    private fun checkQueueUpdates() {
        while (!Thread.currentThread().isInterrupted && !stopped) {
            val item = updatesQueue.take()
            when (item) {
                is Update -> handleUpdate(item)
                is TelegramError -> handleError(item)
                else -> Unit
            }
        }
    }

    fun addHandler(handler: Handler) {
        var handlers = commandHandlers[handler.groupIdentifier]

        if (handlers == null) {
            handlers = arrayListOf()
            commandHandlers[handler.groupIdentifier] = handlers
        }

        handlers.add(handler)
    }

    fun removeHandler(handler: Handler) {
        commandHandlers[handler.groupIdentifier]?.remove(handler)
    }

    fun addErrorHandler(errorHandler: ErrorHandler) {
        errorHandlers.add(errorHandler)
    }

    fun removeErrorHandler(errorHandler: ErrorHandler) {
        errorHandlers.remove(errorHandler)
    }

    private fun handleUpdate(update: Update) {
        for (group in commandHandlers) {
            group.value
                .filter { it.checkUpdate(update) }
                .forEach {
                    try {
                        it.handlerCallback(bot, update)
                    } catch (exc: Exception) {
                        if (logLevel.shouldLogErrors()) {
                            exc.printStackTrace()
                        }
                    }
                }
        }
    }

    private fun handleError(error: TelegramError) {
        errorHandlers.forEach { handleError ->
            try {
                handleError(bot, error)
            } catch (exc: Exception) {
                if (logLevel.shouldLogErrors()) {
                    exc.printStackTrace()
                }
            }
        }
    }

    internal fun stopCheckingUpdates() {
        stopped = true
    }
}
