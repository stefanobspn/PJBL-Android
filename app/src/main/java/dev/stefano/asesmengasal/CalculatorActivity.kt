package dev.stefano.asesmengasal

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import net.objecthunter.exp4j.ExpressionBuilder

class CalculatorActivity : BaseActivity(), View.OnClickListener {

    private lateinit var inputTextView: TextView
    private lateinit var outputTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setChildContent(R.layout.activity_calculator)

        currentNavId = R.id.nav_calculator
        navigationView.setCheckedItem(R.id.nav_calculator)
        setToolbarTitle("Kalkulator")

        inputTextView = findViewById(R.id.input_textview)
        outputTextView = findViewById(R.id.output_textview)

        findViewById<Button>(R.id.button_0).setOnClickListener(this)
        findViewById<Button>(R.id.button_1).setOnClickListener(this)
        findViewById<Button>(R.id.button_2).setOnClickListener(this)
        findViewById<Button>(R.id.button_3).setOnClickListener(this)
        findViewById<Button>(R.id.button_4).setOnClickListener(this)
        findViewById<Button>(R.id.button_5).setOnClickListener(this)
        findViewById<Button>(R.id.button_6).setOnClickListener(this)
        findViewById<Button>(R.id.button_7).setOnClickListener(this)
        findViewById<Button>(R.id.button_8).setOnClickListener(this)
        findViewById<Button>(R.id.button_9).setOnClickListener(this)

        findViewById<Button>(R.id.add_button).setOnClickListener(this)
        findViewById<Button>(R.id.subtract_button).setOnClickListener(this)
        findViewById<Button>(R.id.multiply_button).setOnClickListener(this)
        findViewById<Button>(R.id.divide_button).setOnClickListener(this)
        findViewById<Button>(R.id.dot_button).setOnClickListener(this)

        findViewById<Button>(R.id.clear_button).setOnClickListener {
            inputTextView.text = ""
            outputTextView.text = ""
        }

        findViewById<View>(R.id.backspace_button).setOnClickListener {
            val text = inputTextView.text.toString()
            if (text.isNotEmpty()) {
                inputTextView.text = text.substring(0, text.length - 1)
            }
        }

        findViewById<Button>(R.id.equals_button).setOnClickListener {
            try {
                val expressionText = inputTextView.text.toString()
                    .replace('×', '*')
                    .replace('÷', '/')
                val expression = ExpressionBuilder(expressionText).build()
                val result = expression.evaluate()
                val longResult = result.toLong()
                if (result == longResult.toDouble()) {
                    outputTextView.text = longResult.toString()
                } else {
                    outputTextView.text = result.toString()
                }
            } catch (e: Exception) {
                outputTextView.text = "Error"
            }
        }
    }

    override fun onClick(v: View?) {
        if (v is Button) {
            inputTextView.append(v.text)
        }
    }
}