package com.mobile.micosecha.data.api

import kotlinx.serialization.*

@Serializable
data class ChatMessageSerializable(

    var message: String,
    val id: String

)

fun ChatMessageSerializable.asMessage(): ChatMessage {
    return ChatMessage(
        id = id,
        message = message
    )
}