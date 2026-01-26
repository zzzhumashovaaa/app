package com.example.app_test_1.ui

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.app_test_1.R
import com.example.app_test_1.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val amountInput = findViewById<EditText>(R.id.amountInput)
        val discountInput = findViewById<EditText>(R.id.discountInput)
        val calcButton = findViewById<Button>(R.id.calcButton)
        val resultText = findViewById<TextView>(R.id.resultText)
        val errorText = findViewById<TextView>(R.id.errorText)

        viewModel.state.observe(this) { state ->
            if (state.errorText.isNotBlank()) {
                errorText.visibility = android.view.View.VISIBLE
                resultText.visibility = android.view.View.GONE
                errorText.text = state.errorText
            } else {
                errorText.visibility = android.view.View.GONE
                resultText.visibility = android.view.View.VISIBLE
                resultText.text = state.resultText
            }
        }

        calcButton.setOnClickListener {
            viewModel.calculate(
                amountStr = amountInput.text.toString(),
                discountStr = discountInput.text.toString()
            )
        }
    }
}
