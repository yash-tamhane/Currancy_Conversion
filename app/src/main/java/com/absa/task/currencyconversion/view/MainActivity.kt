package com.absa.task.currencyconversion.view

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.absa.task.currencyconversion.databinding.ActivityMainBinding
import com.absa.task.currencyconversion.view.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var mainBinding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)
    }

    override fun onResume() {
        super.onResume()
        mainBinding.btnConvert.setOnClickListener {
            viewModel.convertCurrency(
                mainBinding.etCurrencyFrom.text.toString(),
                mainBinding.cvFromCurrency.selectedItem.toString(),
                mainBinding.cvToCurrency.selectedItem.toString(),
            )
            val imm: InputMethodManager = getSystemService(
                Context
                    .INPUT_METHOD_SERVICE
            ) as InputMethodManager
            imm.hideSoftInputFromWindow(mainBinding.root.windowToken, 0)
        }
        observeConversionResult()
        handleLoader()
    }

    private fun handleLoader() {
        viewModel.loadingProgress.observe(this) {
            if (it == true) {
                mainBinding.progressBar.visibility = View.VISIBLE
            } else {
                mainBinding.progressBar.visibility = View.GONE
            }
        }
    }

    private fun observeConversionResult() {
        viewModel.conversionResult.observe(this) {
            if (it != null) {
                mainBinding.txtResult.text = it.toString()
            }
        }
    }
}