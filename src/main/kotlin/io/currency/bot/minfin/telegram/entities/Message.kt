package io.currency.bot.minfin.telegram.entities

import com.fasterxml.jackson.databind.PropertyNamingStrategy
import com.fasterxml.jackson.databind.annotation.JsonNaming
import com.github.kotlintelegrambot.entities.*
import com.github.kotlintelegrambot.entities.dice.Dice
import com.github.kotlintelegrambot.entities.files.*
import com.github.kotlintelegrambot.entities.payments.SuccessfulPayment
import io.currency.bot.minfin.telegram.entities.polls.Poll
import io.currency.bot.minfin.telegram.entities.stickers.Sticker

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy::class)
data class Message(
        val messageId: Long,
        val from: User,
        val date: Int,
        val chat: Chat,
        val forwardFrom: User? = null,
        val forwardFromChat: Chat? = null,
        val forwardFromMessageId: Int? = null,
        val forwardSignature: String? = null,
        val forwardSenderName: String? = null,
        val forwardDate: Int? = null,
        val replyToMessage: Message? = null,
        val viaBot: User? = null,
        val editDate: Int? = null,
        val mediaGroupId: String? = null,
        val text: String? = null,
        val entities: List<MessageEntity>? = null,
        val captionEntities: List<MessageEntity>? = null,
        val audio: Audio? = null,
        val document: Document? = null,
        val animation: Animation? = null,
        val dice: Dice? = null,
        val game: Game? = null,
        val photo: List<PhotoSize>? = null,
        val sticker: Sticker? = null,
        val video: Video? = null,
        val voice: Voice? = null,
        val videoNote: VideoNote? = null,
        val caption: String? = null,
        val contact: Contact? = null,
        val location: Location? = null,
        val venue: Venue? = null,
        val newChatMembers: List<User>? = null,
        val poll: Poll? = null,
        val leftChatMember: User? = null,
        val newChatTitle: String? = null,
        val newChatPhoto: List<PhotoSize>? = null,
        val deleteChatPhoto: Boolean? = null,
        val groupChatCreated: Boolean? = null,
        val supergroupChatCreated: Boolean? = null,
        val channelChatCreated: Boolean? = null,
        val migrateToChatId: Long? = null,
        val migrateFromChatId: Long? = null,
        val invoice: Invoice? = null,
        val successfulPayment: SuccessfulPayment? = null,
        val replyMarkup: InlineKeyboardMarkup? = null
)
