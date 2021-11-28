package com.mobile.micosecha.data.services

import com.mobile.micosecha.data.api.Message
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.POST
import retrofit2.http.Body

interface ChatService {
    @POST("/message")
    fun postMessage(@Body body: Message): Call<Void>

    companion object {
        private const val BASE_URL = "http://10.0.2.2:8080/"

        fun create(): ChatService {
            val retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            return retrofit.create(ChatService::class.java)
        }
    }
}