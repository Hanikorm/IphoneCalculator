package com.hanikorm.calculator

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private var temporaryValue = "0"
    private var firstValueEntered = ""
    private var signSelectionVariable = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val display = findViewById<TextView>(R.id.display)

        val buttonResult = findViewById<Button>(R.id.buttonOtv)
        buttonResult.setOnClickListener {
            onButtonResulClicked()
        }

        val buttonPlus = findViewById<Button>(R.id.buttonPlus)
        buttonPlus.setOnClickListener {
            onButtonWithSignClicked("+")
        }

        val buttonMinus = findViewById<Button>(R.id.buttonMinus)
        buttonMinus.setOnClickListener {
            onButtonWithSignClicked("-")
        }

        val buttonDelete = findViewById<Button>(R.id.buttondivide)
        buttonDelete.setOnClickListener {
            onButtonWithSignClicked("/")
        }

        val buttonMultiply = findViewById<Button>(R.id.buttonmultiply)
        buttonMultiply.setOnClickListener {
            onButtonWithSignClicked("*")
        }

        val buttonPlusOrMinus = findViewById<Button>(R.id.buttonplusandminus)
        buttonPlusOrMinus.setOnClickListener {
            onPlusOrMinusButtonClicked()
        }

        val buttonComma = findViewById<Button>(R.id.buttonzp)
        buttonComma.setOnClickListener {
            onCommaButtonClicked()
        }

        val buttonAC = findViewById<Button>(R.id.buttonAC)
        buttonAC.setOnClickListener {
            display.text = "0"
            temporaryValue = "0"
        }

        val buttonNumber9 = findViewById<Button>(R.id.buttonnumber9)
        buttonNumber9.setOnClickListener {
            onNumberButtonClicked("9")
        }

        val buttonNumber8 = findViewById<Button>(R.id.buttonNumber8)
        buttonNumber8.setOnClickListener {
            onNumberButtonClicked("8")
        }

        val buttonNumber7 = findViewById<Button>(R.id.buttonNumber7)
        buttonNumber7.setOnClickListener {
            onNumberButtonClicked("7")
        }

        val buttonNumber6 = findViewById<Button>(R.id.buttonNumber6)
        buttonNumber6.setOnClickListener {
            onNumberButtonClicked("6")
        }

        val buttonNumber5 = findViewById<Button>(R.id.buttonNumber5)
        buttonNumber5.setOnClickListener {
            onNumberButtonClicked("5")
        }

        val buttonNumber4 = findViewById<Button>(R.id.buttonNumber4)
        buttonNumber4.setOnClickListener {
            onNumberButtonClicked("4")
        }

        val buttonNumber3 = findViewById<Button>(R.id.buttonNumber3)
        buttonNumber3.setOnClickListener {
            onNumberButtonClicked("3")
        }

        val buttonNumber2 = findViewById<Button>(R.id.buttonNumber2)
        buttonNumber2.setOnClickListener {
            onNumberButtonClicked("2")
        }

        val buttonNumber1 = findViewById<Button>(R.id.buttonNumber1)
        buttonNumber1.setOnClickListener {
            onNumberButtonClicked("1")
        }

        val buttonNumber0 = findViewById<Button>(R.id.buttonnull)
        buttonNumber0.setOnClickListener {
            if (temporaryValue.isNotEmpty() && temporaryValue != "0") {
                onNumberButtonClicked("0")
            }
        }
    }

    private fun onNumberButtonClicked(number: String) {
        val display = findViewById<TextView>(R.id.display)
        val valueWithoutSign = temporaryValue.trimStart('-')
        if (valueWithoutSign.length < 9) {
            temporaryValue = if (temporaryValue == "0" && number == "0") {
                "0"
            } else if (temporaryValue == "-0" && number == "0") {
                "-0"
            } else if (temporaryValue == "0" || temporaryValue == "-0") {
                if (temporaryValue.startsWith("-")) {
                    "-$number"
                } else {
                    number
                }
            } else {
                display.text.toString().replace(" ", "") + number
            }
        }
    }
    private fun onCommaButtonClicked() {
        val display = findViewById<TextView>(R.id.display)
        if (!temporaryValue.contains(".")) {
            temporaryValue = display.text.toString() + "."
            display.text = temporaryValue
        }
    }

    private fun onPlusOrMinusButtonClicked() {
        val display = findViewById<TextView>(R.id.display)
        temporaryValue = if (temporaryValue.contains('-')) {
            temporaryValue.drop(1)
        } else {
            "-" + display.text.toString()

        }
        display.text = temporaryValue
    }

    private fun onButtonWithSignClicked(sign: String) {
        firstValueEntered = temporaryValue
        temporaryValue = "0"
        signSelectionVariable = sign
    }

    private fun onButtonResulClicked() {
        val display = findViewById<TextView>(R.id.display)
        when (signSelectionVariable) {
            "+" -> {
                temporaryValue = (firstValueEntered.toDouble() + temporaryValue.toDouble()).toString()
                dropLastNumberOnClicked()
            }
            "-" -> {
                temporaryValue = (firstValueEntered.toDouble() - temporaryValue.toDouble()).toString()
                dropLastNumberOnClicked()
            }
            "*" -> {
                temporaryValue = (firstValueEntered.toDouble() * temporaryValue.toDouble()).toString()
                dropLastNumberOnClicked()
            }
            "/" -> {
                temporaryValue = (firstValueEntered.toDouble() / temporaryValue.toDouble()).toString()
                dropLastNumberOnClicked()
            }
        }
        display.text = formatNumber(temporaryValue)
    }

    private fun formatNumber(value: String): String {
        val number = value.toDouble()
        return if (number == number.toLong().toDouble()) {
            number.toLong().toString()
        } else {
            String.format("%.10f", number).trimEnd('0').trimEnd('.')
        }
    }

    private fun dropLastNumberOnClicked() {
        val display = findViewById<TextView>(R.id.display)
        if (temporaryValue.endsWith(".0")) {
            temporaryValue = temporaryValue.dropLast(2)
        }
        display.text = temporaryValue
    }


}