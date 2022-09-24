package com.mobile.micosecha.data.repository

import com.mobile.micosecha.data.api.ChatMessageSerializable
import com.mobile.micosecha.data.api.VariablesDataResponse
import com.mobile.micosecha.data.repository.remote.KtorWebService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MainRepository(
    private val ktorWebService: KtorWebService
) {

    suspend fun getBotResponse(chatMessage: String): ChatMessageSerializable
            = withContext(Dispatchers.IO) {
        return@withContext ktorWebService.getBotResponse(chatMessage)
    }

    suspend fun getGraphResponse(variety: String): VariablesDataResponse
            = withContext(Dispatchers.IO) {
        return@withContext ktorWebService.getGraphResponse(variety)
    }
}