package com.mobile.micosecha.data.api

import kotlinx.serialization.*

@Serializable
data class VariablesDataResponse(

    var lineSet: LinkedHashMap<String, Float>,
    var min_temp: Float,
    var max_temp: Float,
    var rhum: Float,
    var sbright: Float,
    var prec: Float,
    var predictedLineSet: LinkedHashMap<String, Float>,
    var yield_data: ArrayList<String>,
    var prod: Float

)

fun VariablesDataResponse.asMessage(): VariablesData {
    return VariablesData(
        lineSet = lineSet,
        min_temp = min_temp,
        max_temp = max_temp,
        rhum = rhum,
        sbright = sbright,
        prec = prec,
        predictedLineSet = predictedLineSet,
        yield_data = yield_data,
        prod = prod
    )
}