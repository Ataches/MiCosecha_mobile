package com.mobile.micosecha.data.api

data class VariablesData (
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