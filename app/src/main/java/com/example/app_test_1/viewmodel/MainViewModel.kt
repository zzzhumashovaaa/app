package com.example.app_test_1.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.app_test_1.model.UiState

class MainViewModel : ViewModel() {

    private val _state = MutableLiveData(UiState())
    val state: LiveData<UiState> = _state

    fun calculate(amountStr: String, discountStr: String) {
        val amount = amountStr.trim().toDoubleOrNull()
        val discount = discountStr.trim().toDoubleOrNull()

        if (amount == null || discount == null) {
            _state.value = UiState(
                resultText = "",
                errorText = "Введите корректные числа",
                isValid = false
            )
            return
        }

        if (amount < 0 || discount < 0) {
            _state.value = UiState(
                resultText = "",
                errorText = "Значения не могут быть отрицательными",
                isValid = false
            )
            return
        }

        if (discount !in 0.0..90.0) {
            _state.value = UiState(
                resultText = "",
                errorText = "Скидка должна быть от 0 до 90%",
                isValid = false
            )
            return
        }

        val finalAmount = amount * (1 - discount / 100)
        _state.value = UiState(
            resultText = "Итоговая сумма: %.2f ₸".format(finalAmount),
            errorText = "",
            isValid = true
        )
    }
}
