package com.absa.task.currencyconversion.view.model

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CurrencyApiClient {
    companion object {
        private var retrofit: CurrencyApiRequest? = null
        private const val BASE_URL = "https://api.getgeoapi.com/v2/currency/"
        fun getClient(): CurrencyApiRequest? {
            retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(CurrencyApiRequest::class.java)
            return retrofit
        }
    }
}