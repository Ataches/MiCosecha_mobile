package com.mobile.micosecha.data.api

import kotlinx.serialization.*

@Serializable
data class VariablesDataResponse(

    var min_temp: Float,
    var max_temp: Float,
    var rhum: Float,
    var sbright: Float,
    var prec: Float,
    var prod: Float

)

fun VariablesData.asMessage(): VariablesData {
    return VariablesData(
        min_temp= min_temp,
        max_temp= max_temp,
        rhum= rhum,
        sbright= sbright,
        prec= prec,
        prod= prod
    )
}