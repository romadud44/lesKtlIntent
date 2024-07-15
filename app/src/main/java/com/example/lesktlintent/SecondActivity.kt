package com.example.lesktlintent

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SecondActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var toolbarSecond: Toolbar
    private lateinit var backToMainBTN: Button
    private lateinit var firstOperandET: EditText
    private lateinit var secondOperandET: EditText

    private lateinit var sumBTN: Button
    private lateinit var subBTN: Button
    private lateinit var multBTN: Button
    private lateinit var divBTN: Button


    private var result: Double = 0.0

    private fun findSecondId() {
        toolbarSecond = findViewById(R.id.toolbarSecond)
        setSupportActionBar(toolbarSecond)
        title = "Скрытый калькулятор"
        toolbarSecond.subtitle = "версия 1.0"
        backToMainBTN = findViewById(R.id.backToMainBTN)
        firstOperandET = findViewById(R.id.firstOperandET)
        secondOperandET = findViewById(R.id.secondOperandET)

        sumBTN = findViewById(R.id.sumBTN)
        subBTN = findViewById(R.id.subBTN)
        multBTN = findViewById(R.id.multBTN)
        divBTN = findViewById(R.id.divBTN)

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_second)
        findSecondId()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        sumBTN.setOnClickListener(this)
        sumBTN.setOnClickListener(this)
        multBTN.setOnClickListener(this)
        divBTN.setOnClickListener(this)

        backToMainBTN.setOnClickListener {
            val intent = Intent()
            intent.putExtra("calcResult", result.toString())
            setResult(RESULT_OK, intent)
            finish()
        }

    }

    override fun onClick(v: View?) {
        if (firstOperandET.text.isEmpty() || secondOperandET.text.isEmpty()) {
            return
        }
        var first = firstOperandET.text.toString().toDouble()
        var second = secondOperandET.text.toString().toDouble()

// Хотел добавить Toast к каждой операции, но все залагало почему то

        result = when (v?.id) {
            R.id.sumBTN -> {
//                Toast.makeText(this, "Сложение", Toast.LENGTH_LONG).show()
                Operations(first, second).sum()
            }

            R.id.subBTN -> {
//                Toast.makeText(this, "Деление", Toast.LENGTH_LONG).show()
                Operations(first, second).sub()
            }

            R.id.multBTN -> {
//                Toast.makeText(this, "Умножение", Toast.LENGTH_LONG).show()
                Operations(first, second).mult()
            }

            R.id.divBTN -> {
//                Toast.makeText(this, "Деление", Toast.LENGTH_LONG).show()
                Operations(first, second).div()
            }

            else -> 0.0
        }

    }
}