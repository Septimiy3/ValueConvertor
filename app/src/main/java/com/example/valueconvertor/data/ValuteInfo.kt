package com.example.valueconvertor.data

import com.google.gson.annotations.SerializedName

data class ValuteInfo(

    @SerializedName("Valute")
    var valutes: Map<String, Valute>
)

data class Valute(

    @SerializedName("CharCode")
    var charCode: String,
    @SerializedName("Nominal")
    var nominal: Int,
    @SerializedName("Name")
    var name: String,
    @SerializedName("Value")
    var value: Double
)

