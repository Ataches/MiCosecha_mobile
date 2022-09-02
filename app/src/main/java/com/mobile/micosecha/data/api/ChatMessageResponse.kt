package com.mobile.micosecha.data.api

import kotlinx.serialization.*

@Serializable
data class ChatMessageResponse(

    var message: String,
    val id: String

)

fun ChatMessageResponse.asMessage(): ChatMessage {
    return ChatMessage(
        id = id,
        message = message
    )
}