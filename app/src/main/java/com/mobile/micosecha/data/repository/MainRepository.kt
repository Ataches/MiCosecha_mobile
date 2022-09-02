package com.mobile.micosecha.data.repository

import com.mobile.micosecha.data.api.ChatMessageResponse
import com.mobile.micosecha.data.repository.remote.KtorWebService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MainRepository(
    private val ktorWebService: KtorWebService
) {

    suspend fun getBotResponse(chatMessage: String): ChatMessageResponse
            = withContext(Dispatchers.IO) {
        return@withContext ktorWebService.getBotResponse(chatMessage)
    }
}