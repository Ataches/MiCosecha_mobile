package com.mobile.micosecha.util

import com.mobile.micosecha.data.api.VariablesDataResponse
import com.mobile.micosecha.data.repository.MainRepository
import com.mobile.micosecha.data.repository.remote.KtorWebService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GraphResponse {

    private val repository = MainRepository(
        KtorWebService()
    )

    suspend fun graphResponse(variety: String): VariablesDataResponse = withContext(Dispatchers.IO) {
        return@withContext repository.getGraphResponse(variety)
    }

}