package com.xuweic.dotify

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private val randomNumber = Random.nextInt(0, 999999999)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tvPlayTimes = findViewById<TextView>(R.id.tvPlayTimes)
        tvPlayTimes.text = "$randomNumber plays"
        val tvName = findViewById<EditText>(R.id.tvName)
        tvName.isEnabled = false;
    }

    fun addPlayTimes(view: View) {
        val tvPlayTimes = findViewById<TextView>(R.id.tvPlayTimes)
        val input = tvPlayTimes.text.split(" ")
        val output = input[0].toInt() + 1
        tvPlayTimes.text = "$output plays"
    }

    fun skipPrevious(view: View) {
        Toast.makeText(this, "Skipping to previous track", Toast.LENGTH_SHORT).show()
    }

    fun skipNext(view: View) {
        Toast.makeText(this, "Skipping to next track", Toast.LENGTH_SHORT).show()
    }

    fun textReader(view: View) {
        val btChangeUser = findViewById<Button>(R.id.btChangeUser)
        val inputFromButton = btChangeUser.text
        val tvName = findViewById<EditText>(R.id.tvName)
        val input = tvName.text.toString();
        if (input.isNotEmpty()) {
            tvName.hint = ""
            if (inputFromButton == "Change User") {
                tvName.isEnabled = true;
                btChangeUser.text = "Apply"
            } else {
                tvName.isEnabled = false;
                btChangeUser.text = "Change User"
            }
        } else {
            tvName.hint = "Please enter username"
        }
    }
}

