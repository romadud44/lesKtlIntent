package com.example.lesktlintent

import android.widget.Toast

class Operations(private val first: Double, private val second: Double) {
    fun sum() = first + second
    fun sub() = first - second
    fun mult() = first * second
    fun div() = first / second
}