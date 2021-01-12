package io.currency.bot.minfin.telegram.dispatcher

import io.currency.bot.minfin.telegram.dispatcher.handlers.CallbackQueryHandler
import io.currency.bot.minfin.telegram.dispatcher.handlers.ChannelHandler
import io.currency.bot.minfin.telegram.dispatcher.handlers.CommandHandler
import io.currency.bot.minfin.telegram.dispatcher.handlers.ContactHandler
import io.currency.bot.minfin.telegram.dispatcher.handlers.DiceHandler
import io.currency.bot.minfin.telegram.dispatcher.handlers.ErrorHandler
import io.currency.bot.minfin.telegram.dispatcher.handlers.HandleAnimation
import io.currency.bot.minfin.telegram.dispatcher.handlers.HandleAudio
import io.currency.bot.minfin.telegram.dispatcher.handlers.HandleCallbackQuery
import io.currency.bot.minfin.telegram.dispatcher.handlers.HandleChannelPost
import io.currency.bot.minfin.telegram.dispatcher.handlers.HandleCommand
import io.currency.bot.minfin.telegram.dispatcher.handlers.HandleContact
import io.currency.bot.minfin.telegram.dispatcher.handlers.HandleDice
import io.currency.bot.minfin.telegram.dispatcher.handlers.HandleDocument
import io.currency.bot.minfin.telegram.dispatcher.handlers.HandleError
import io.currency.bot.minfin.telegram.dispatcher.handlers.HandleGame
import io.currency.bot.minfin.telegram.dispatcher.handlers.HandleInlineQuery
import io.currency.bot.minfin.telegram.dispatcher.handlers.HandleLocation
import io.currency.bot.minfin.telegram.dispatcher.handlers.HandleMessage
import io.currency.bot.minfin.telegram.dispatcher.handlers.HandleNewChatMembers
import io.currency.bot.minfin.telegram.dispatcher.handlers.HandlePhotos
import io.currency.bot.minfin.telegram.dispatcher.handlers.HandlePollAnswer
import io.currency.bot.minfin.telegram.dispatcher.handlers.HandlePreCheckoutQuery
import io.currency.bot.minfin.telegram.dispatcher.handlers.HandleSticker
import io.currency.bot.minfin.telegram.dispatcher.handlers.HandleText
import io.currency.bot.minfin.telegram.dispatcher.handlers.HandleVideo
import io.currency.bot.minfin.telegram.dispatcher.handlers.HandleVideoNote
import io.currency.bot.minfin.telegram.dispatcher.handlers.HandleVoice
import com.github.kotlintelegrambot.dispatcher.handlers.InlineQueryHandler
import com.github.kotlintelegrambot.dispatcher.handlers.LocationHandler
import com.github.kotlintelegrambot.dispatcher.handlers.MessageHandler
import com.github.kotlintelegrambot.dispatcher.handlers.NewChatMembersHandler
import com.github.kotlintelegrambot.dispatcher.handlers.PollAnswerHandler
import com.github.kotlintelegrambot.dispatcher.handlers.PreCheckoutQueryHandler
import com.github.kotlintelegrambot.dispatcher.handlers.TextHandler
import io.currency.bot.minfin.telegram.dispatcher.handlers.media.AnimationHandler
import io.currency.bot.minfin.telegram.dispatcher.handlers.media.AudioHandler
import io.currency.bot.minfin.telegram.dispatcher.handlers.media.DocumentHandler
import io.currency.bot.minfin.telegram.dispatcher.handlers.media.GameHandler
import com.github.kotlintelegrambot.dispatcher.handlers.media.PhotosHandler
import io.currency.bot.minfin.telegram.dispatcher.handlers.media.StickerHandler
import com.github.kotlintelegrambot.dispatcher.handlers.media.VideoHandler
import com.github.kotlintelegrambot.dispatcher.handlers.media.VideoNoteHandler
import com.github.kotlintelegrambot.dispatcher.handlers.media.VoiceHandler
import io.currency.bot.minfin.telegram.extensions.filters.Filter
import io.currency.bot.minfin.telegram.extensions.filters.Filter.All

fun Dispatcher.message(handleMessage: HandleMessage) {
    addHandler(MessageHandler(All, handleMessage))
}

fun Dispatcher.message(filter: Filter, handleMessage: HandleMessage) {
    addHandler(MessageHandler(filter, handleMessage))
}

fun Dispatcher.command(command: String, handleCommand: HandleCommand) {
    addHandler(CommandHandler(command, handleCommand))
}

fun Dispatcher.text(text: String? = null, handleText: HandleText) {
    addHandler(TextHandler(text, handleText))
}

fun Dispatcher.callbackQuery(data: String? = null, handleCallbackQuery: HandleCallbackQuery) {
    addHandler(CallbackQueryHandler(callbackData = data, handleCallbackQuery = handleCallbackQuery))
}

fun Dispatcher.callbackQuery(
    callbackData: String? = null,
    callbackAnswerText: String? = null,
    callbackAnswerShowAlert: Boolean? = null,
    callbackAnswerUrl: String? = null,
    callbackAnswerCacheTime: Int? = null,
    handleCallbackQuery: HandleCallbackQuery
) {
    addHandler(
        CallbackQueryHandler(
            callbackData = callbackData,
            callbackAnswerText = callbackAnswerText,
            callbackAnswerShowAlert = callbackAnswerShowAlert,
            callbackAnswerUrl = callbackAnswerUrl,
            callbackAnswerCacheTime = callbackAnswerCacheTime,
            handleCallbackQuery = handleCallbackQuery
        )
    )
}

fun Dispatcher.contact(handleContact: HandleContact) {
    addHandler(ContactHandler(handleContact))
}

fun Dispatcher.location(handleLocation: HandleLocation) {
    addHandler(LocationHandler(handleLocation))
}

fun Dispatcher.telegramError(handleError: HandleError) {
    addErrorHandler(ErrorHandler(handleError))
}

fun Dispatcher.preCheckoutQuery(body: HandlePreCheckoutQuery) {
    addHandler(PreCheckoutQueryHandler(body))
}

fun Dispatcher.channel(body: HandleChannelPost) {
    addHandler(ChannelHandler(body))
}

fun Dispatcher.inlineQuery(body: HandleInlineQuery) {
    addHandler(InlineQueryHandler(body))
}

fun Dispatcher.audio(body: HandleAudio) {
    addHandler(AudioHandler(body))
}

fun Dispatcher.document(body: HandleDocument) {
    addHandler(DocumentHandler(body))
}

fun Dispatcher.animation(body: HandleAnimation) {
    addHandler(AnimationHandler(body))
}

fun Dispatcher.game(body: HandleGame) {
    addHandler(GameHandler(body))
}

fun Dispatcher.photos(body: HandlePhotos) {
    addHandler(PhotosHandler(body))
}

fun Dispatcher.sticker(body: HandleSticker) {
    addHandler(StickerHandler(body))
}

fun Dispatcher.video(body: HandleVideo) {
    addHandler(VideoHandler(body))
}

fun Dispatcher.voice(body: HandleVoice) {
    addHandler(VoiceHandler(body))
}

fun Dispatcher.videoNote(body: HandleVideoNote) {
    addHandler(VideoNoteHandler(body))
}

fun Dispatcher.newChatMembers(body: HandleNewChatMembers) {
    addHandler(NewChatMembersHandler(body))
}

fun Dispatcher.pollAnswer(body: HandlePollAnswer) {
    addHandler(PollAnswerHandler(body))
}

fun Dispatcher.dice(body: HandleDice) {
    addHandler(DiceHandler(body))
}
