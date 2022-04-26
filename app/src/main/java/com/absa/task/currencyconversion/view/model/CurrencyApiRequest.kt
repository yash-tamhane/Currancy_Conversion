package com.absa.task.currencyconversion.view.model

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CurrencyApiRequest {

    @GET("convert?api_key=8bf5aed085b7cb0650976526fa6367ef6f42edaa")
    suspend fun getRates(
        @Query("from") base_currency_code: String,
        @Query("to") conversion_currency: String,
        @Query("amount") conversion_amount: String
    ): Response<CurrencyResult>
}