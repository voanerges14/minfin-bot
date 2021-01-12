package io.currency.bot.minfin.telegram.dispatcher.handlers.media

import com.github.kotlintelegrambot.dispatcher.handlers.media.MediaHandler
import io.currency.bot.minfin.telegram.dispatcher.handlers.HandleDocument
import io.currency.bot.minfin.telegram.entities.Message
import io.currency.bot.minfin.telegram.entities.Update
import com.github.kotlintelegrambot.entities.files.Document

internal class DocumentHandler(
    handleDocument: HandleDocument
) : MediaHandler<Document>(
    handleDocument,
    DocumentHandlerFunctions::mapMessageToDocument,
    DocumentHandlerFunctions::isUpdateDocument
)

private object DocumentHandlerFunctions {

    fun mapMessageToDocument(message: Message): Document {
        val document = message.document
        checkNotNull(document)
        return document
    }

    fun isUpdateDocument(update: Update): Boolean = update.message?.document != null
}
