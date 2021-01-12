package io.currency.bot.minfin.telegram.dispatcher.handlers.media

import io.currency.bot.minfin.telegram.dispatcher.handlers.HandleAnimation
import com.github.kotlintelegrambot.dispatcher.handlers.media.MediaHandler
import io.currency.bot.minfin.telegram.entities.Message
import io.currency.bot.minfin.telegram.entities.Update
import com.github.kotlintelegrambot.entities.files.Animation

internal class AnimationHandler(
    handleAnimation: HandleAnimation
) : MediaHandler<Animation>(
    handleAnimation,
    AnimationHandlerFunctions::mapMessageToAnimation,
    AnimationHandlerFunctions::updateIsAnimation
)

private object AnimationHandlerFunctions {

    fun mapMessageToAnimation(message: Message): Animation {
        checkNotNull(message.animation)
        return message.animation
    }

    fun updateIsAnimation(update: Update): Boolean = update.message?.animation != null
}
