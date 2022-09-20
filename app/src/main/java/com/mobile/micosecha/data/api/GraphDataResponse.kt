package com.mobile.micosecha.data.api

import kotlinx.serialization.*

@Serializable
data class GraphDataResponse(

    var lineSetFirst: LinkedHashMap<String, Float>,
    var lineSetSecond: LinkedHashMap<String, Float>,
    var lineSetThird: LinkedHashMap<String, Float>,
    var lineSetFourth: LinkedHashMap<String, Float>

)

fun GraphDataResponse.asMessage(): GraphData {
    return GraphData(
        lineSetFirst= lineSetFirst,
        lineSetSecond= lineSetSecond,
        lineSetThird= lineSetThird,
        lineSetFourth= lineSetFourth    
    )
}