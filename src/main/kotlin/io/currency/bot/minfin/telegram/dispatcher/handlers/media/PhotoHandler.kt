package com.github.kotlintelegrambot.dispatcher.handlers.media

import io.currency.bot.minfin.telegram.dispatcher.handlers.HandlePhotos
import io.currency.bot.minfin.telegram.entities.Message
import io.currency.bot.minfin.telegram.entities.Update
import com.github.kotlintelegrambot.entities.files.PhotoSize

internal class PhotosHandler(
    handlePhotos: HandlePhotos
) : MediaHandler<List<PhotoSize>>(
    handlePhotos,
    PhotosHandlerFunctions::mapMessageToPhotos,
    PhotosHandlerFunctions::isUpdatePhotos
)

private object PhotosHandlerFunctions {

    fun mapMessageToPhotos(message: Message): List<PhotoSize> {
        val photos = message.photo
        checkNotNull(photos)
        return photos
    }

    fun isUpdatePhotos(update: Update): Boolean {
        val photos = update.message?.photo

        return photos != null && photos.isNotEmpty()
    }
}
