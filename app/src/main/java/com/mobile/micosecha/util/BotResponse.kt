package com.mobile.micosecha.util

import com.mobile.micosecha.data.api.ChatMessageResponse
import com.mobile.micosecha.data.repository.MainRepository
import com.mobile.micosecha.data.repository.remote.KtorWebService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class BotResponse {

    private val repository = MainRepository(
        KtorWebService()
    )

    suspend fun basicResponses(chatMessage: String): ChatMessageResponse = withContext(Dispatchers.IO) {
        return@withContext repository.getBotResponse(filterString(chatMessage))
    }

    private fun filterString(message: String): String {
        return message.replace("?", "").replace(",", "")
            .replace(".", "").replace("!", "")
    }

}