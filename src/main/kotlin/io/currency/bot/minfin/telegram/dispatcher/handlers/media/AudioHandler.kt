package io.currency.bot.minfin.telegram.dispatcher.handlers.media

import io.currency.bot.minfin.telegram.dispatcher.handlers.HandleAudio
import com.github.kotlintelegrambot.dispatcher.handlers.media.MediaHandler
import io.currency.bot.minfin.telegram.entities.Message
import io.currency.bot.minfin.telegram.entities.Update
import com.github.kotlintelegrambot.entities.files.Audio

internal class AudioHandler(
    handleAudio: HandleAudio
) : MediaHandler<Audio>(
    handleAudio,
    AudioHandlerFunctions::mapMessageToAudio,
    AudioHandlerFunctions::isUpdateAudio
)

private object AudioHandlerFunctions {

    fun mapMessageToAudio(message: Message): Audio {
        val audio = message.audio
        checkNotNull(audio)
        return audio
    }

    fun isUpdateAudio(update: Update): Boolean = update.message?.audio != null
}
