package com.github.kotlintelegrambot.dispatcher.handlers.media

import io.currency.bot.minfin.telegram.dispatcher.handlers.HandleVideoNote
import io.currency.bot.minfin.telegram.entities.Message
import io.currency.bot.minfin.telegram.entities.Update
import com.github.kotlintelegrambot.entities.files.VideoNote

internal class VideoNoteHandler(
    handleVideoNote: HandleVideoNote
) : MediaHandler<VideoNote>(
    handleVideoNote,
    VideoNoteHandlerFunctions::mapMessageToVideoNote,
    VideoNoteHandlerFunctions::isUpdateVideoNote
)

private object VideoNoteHandlerFunctions {

    fun mapMessageToVideoNote(message: Message): VideoNote {
        val videoNote = message.videoNote
        checkNotNull(videoNote)
        return videoNote
    }

    fun isUpdateVideoNote(update: Update): Boolean = update.message?.videoNote != null
}
