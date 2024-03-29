package com.mobile.micosecha.data.repository.remote

import com.mobile.micosecha.data.api.ChatMessageSerializable
import com.mobile.micosecha.data.api.VariablesDataResponse
import com.mobile.micosecha.data.api.VariablesDataSerializable
import com.mobile.micosecha.util.Constants
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.*

class KtorWebService {
    private val ktorHttpClient = HttpClient(CIO) {
        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
            })
        }
    }

    suspend fun getBotResponse(chatMessage: String): ChatMessageSerializable {
        return ktorHttpClient.post("http://192.168.56.1:5000/") {
            contentType(ContentType.Application.Json)
            setBody(ChatMessageSerializable(message = chatMessage, id = Constants.SEND_ID))
        }.body()
    }

    suspend fun getGraphResponse(variety: String): VariablesDataResponse {
        return ktorHttpClient.post("http://192.168.56.1:5001/") {
            contentType(ContentType.Application.Json)
            setBody(VariablesDataSerializable(variety))
        }.body()
    }
}