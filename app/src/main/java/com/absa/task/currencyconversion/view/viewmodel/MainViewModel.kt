package com.absa.task.currencyconversion.view.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.absa.task.currencyconversion.R
import com.absa.task.currencyconversion.view.model.CurrencyApiClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {

    var conversionResult: MutableLiveData<Any> = MutableLiveData()
    val loadingProgress: MutableLiveData<Boolean> = MutableLiveData()

    fun convertCurrency(amountStr: String, fromCurrency: String, toCurrency: String) {
        val fromAmount = amountStr.toFloatOrNull()
        if (fromAmount == null) {
            conversionResult.value = getApplication<Application?>()
                .applicationContext.getString(R.string.invalid_amount)
            return
        }
        loadingProgress.value = true
        viewModelScope.launch(Dispatchers.IO) {
            val responseRate = CurrencyApiClient
                .getClient()?.getRates(fromCurrency, toCurrency, amountStr)

            if (responseRate != null) {
                loadingProgress.postValue(false)
                if (responseRate.body()?.amount != null) {
                    val map = responseRate.body()!!.amount
                    map.keys.forEach {
                        val rateForAmount = map[it]?.rate_for_amount
                        conversionResult.postValue(
                            "$fromAmount $fromCurrency" +
                                    " = $rateForAmount $toCurrency"
                        )
                    }
                }
            }
        }
    }
}