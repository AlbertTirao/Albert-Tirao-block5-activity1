package com.example.tirao_albert_block5_p1_quiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private lateinit var display: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        display = findViewById(R.id.display)
    }

    fun onDigit(view: View) {
        val digit = (view as Button).text.toString()
        val currentValue = display.text.toString()
        val newValue = currentValue + digit
        display.text = newValue
    }

    fun onDecimal(view: View) {
        val currentValue = display.text.toString()
        if (!currentValue.contains(".")) {
            val newValue = currentValue + "."
            display.text = newValue
        }
    }

    fun onOperator(view: View) {
        val operator = (view as Button).text.toString()
        val currentValue = display.text.toString()

        if (currentValue.isNotEmpty() && !isOperatorAdded(currentValue)) {
            val newValue = currentValue + operator
            display.text = newValue
        }
    }

    fun onDelete(view: View) {
        val currentValue = display.text.toString()
        if (currentValue.isNotEmpty()) {
            val newValue = currentValue.dropLast(1)
            display.text = newValue
        }
    }

    fun onClear(view: View) {
        display.text = ""
    }

    fun onEqual(view: View) {
        val value = display.text.toString()

        if (value.isNotEmpty() && isOperatorAdded(value)) {
            val parts = value.split(Regex("[-+*/]"))
            if (parts.size == 2) {
                val operator = value.replace(Regex("[^\\+\\-\\*/]+"), "")
                val operand1 = parts[0].toDouble()
                val operand2 = parts[1].toDouble()

                val result = when (operator) {
                    "+" -> operand1 + operand2
                    "-" -> operand1 - operand2
                    "*" -> operand1 * operand2
                    "/" -> if (operand2 != 0.0) operand1 / operand2 else Double.NaN
                    else -> Double.NaN
                }

                display.text = result.toString()
            } else {
                display.text = "Error"
            }
        }
    }

    fun isOperatorAdded(value: String): Boolean {
        val operators = listOf("+", "-", "*", "/")
        return operators.any { value.contains(it) }
    }
}

