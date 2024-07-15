package com.example.lesktlintent

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private lateinit var toolbarMain: Toolbar
    private lateinit var buttonToCalcBTN: Button
    private lateinit var resultTV: TextView

    private fun findId() {
        toolbarMain = findViewById(R.id.toolbarMain)
        setSupportActionBar(toolbarMain)
        title = "Скрытый калькулятор"
        toolbarMain.subtitle = "версия 1.0"

        buttonToCalcBTN = findViewById(R.id.buttonToCalcBTN)
        resultTV = findViewById(R.id.resultTV)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        findId()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        buttonToCalcBTN.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
            launchResult.launch(intent)

        }

    }
    private val launchResult = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ){result ->
        if(result.resultCode == RESULT_OK) {
            val data = result.data
            val calcResult = data?.getStringExtra("calcResult")
            resultTV.text = calcResult
            Toast.makeText(this, "Результат: $calcResult", Toast.LENGTH_LONG).show()
        } else{
            Toast.makeText(this, "Ошибка", Toast.LENGTH_LONG).show()
        }
    }
}