package io.currency.bot.minfin.telegram.dispatcher.handlers.media

import com.github.kotlintelegrambot.dispatcher.handlers.media.MediaHandler
import io.currency.bot.minfin.telegram.dispatcher.handlers.HandleGame
import io.currency.bot.minfin.telegram.entities.Game
import io.currency.bot.minfin.telegram.entities.Message
import io.currency.bot.minfin.telegram.entities.Update

internal class GameHandler(
    handleGame: HandleGame
) : MediaHandler<Game>(
    handleGame,
    GameHandlerFunctions::mapMessageToGame,
    GameHandlerFunctions::isUpdateGame
)

private object GameHandlerFunctions {

    fun mapMessageToGame(message: Message): Game {
        val game = message.game
        checkNotNull(game)
        return game
    }

    fun isUpdateGame(update: Update): Boolean = update.message?.game != null
}
