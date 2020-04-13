package com.xuweic.dotify

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.Color.*
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random
class MainActivity : AppCompatActivity() {

    private val randomNumber = Random.nextInt(0, 999999999)

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tvPlayTimes = findViewById<TextView>(R.id.tvPlayTimes)
        tvPlayTimes.text = "$randomNumber plays"
        val tvName = findViewById<EditText>(R.id.tvName)
        tvName.isEnabled = false;
        val ivAlbum = findViewById<ImageView>(R.id.ivAlbum)
        val tvSong = findViewById<TextView>(R.id.tvSong)
        val singer = findViewById<TextView>(R.id.singer)
        var toggleColor = true;
        ivAlbum.setOnLongClickListener{
            toggleColor = if (toggleColor) {
                tvName.setTextColor(RED)
                tvSong.setTextColor(RED)
                singer.setTextColor(RED)
                tvPlayTimes.setTextColor(RED)
                false
            } else {
                tvName.setTextColor(BLACK)
                tvSong.setTextColor(BLACK)
                singer.setTextColor(BLACK)
                tvPlayTimes.setTextColor(BLACK)
                true
            }
            true
        }
    }

    @SuppressLint("SetTextI18n")
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


