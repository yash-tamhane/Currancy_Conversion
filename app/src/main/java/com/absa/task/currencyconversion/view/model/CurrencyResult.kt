package com.absa.task.currencyconversion.view.model

import com.google.gson.annotations.SerializedName

data class CurrencyResult(
    @SerializedName("base_currency_code")
    var baseCurrency: String,
    @SerializedName("updated_date")
    var date: String,
    @SerializedName("rates")
    var amount: HashMap<String, Rates> = HashMap(),
    @SerializedName("status")
    var isResult: Boolean
)

