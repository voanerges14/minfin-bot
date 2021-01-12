package io.currency.bot.minfin.telegram.dispatcher.handlers.media

import com.github.kotlintelegrambot.dispatcher.handlers.media.MediaHandler
import io.currency.bot.minfin.telegram.dispatcher.handlers.HandleSticker
import io.currency.bot.minfin.telegram.entities.Message
import io.currency.bot.minfin.telegram.entities.Update
import io.currency.bot.minfin.telegram.entities.stickers.Sticker

internal class StickerHandler(
    handleSticker: HandleSticker
) : MediaHandler<Sticker>(
    handleSticker,
    StickerHandlerFunctions::mapMessageToSticker,
    StickerHandlerFunctions::isUpdateSticker
)

private object StickerHandlerFunctions {

    fun mapMessageToSticker(message: Message): Sticker {
        val sticker = message.sticker
        checkNotNull(sticker)
        return sticker
    }

    fun isUpdateSticker(update: Update): Boolean = update.message?.sticker != null
}
