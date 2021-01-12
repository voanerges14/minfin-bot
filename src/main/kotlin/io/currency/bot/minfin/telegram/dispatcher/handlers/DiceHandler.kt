package io.currency.bot.minfin.telegram.dispatcher.handlers

import io.currency.bot.minfin.telegram.Bot
import io.currency.bot.minfin.telegram.entities.Message
import io.currency.bot.minfin.telegram.entities.Update
import com.github.kotlintelegrambot.entities.dice.Dice

data class DiceHandlerEnvironment(
        val bot: Bot,
        val update: Update,
        val message: Message,
        val dice: Dice
)

internal class DiceHandler(handleDice: HandleDice) : Handler(HandleDiceProxy(handleDice)) {
    override val groupIdentifier: String
        get() = "dice"

    override fun checkUpdate(update: Update): Boolean = update.message?.dice != null
}

private class HandleDiceProxy(private val handleDice: HandleDice) :
        HandleUpdate {
    override fun invoke(bot: Bot, update: Update) {
        val message = update.message
        val dice = message?.dice
        checkNotNull(dice)

        val diceHandlerEnv = DiceHandlerEnvironment(bot, update, message, dice)
        handleDice.invoke(diceHandlerEnv)
    }
}
