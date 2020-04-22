package com.xuweic.dotify

import android.annotation.SuppressLint
import android.graphics.Color.BLACK
import android.graphics.Color.RED
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import android.view.View
import com.ericchee.songdataprovider.Song
import kotlin.random.Random
class MainActivity : AppCompatActivity() {


    private val randomNumber = Random.nextInt(0, 999999999)
    companion object {
        const val NAME_KEY = "NAME_KEY"
        const val ALBUM_KEY = "ALBUM_KEY"
        const val SONG_KEY = "SONG_KEY"
    }
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val songName = intent.getStringExtra(SONG_KEY)
        val albumSrc = intent.getIntExtra(ALBUM_KEY,-1)
        val singerName = intent.getStringExtra(NAME_KEY)

        //setting random playtimes
        val tvPlayTimes = findViewById<TextView>(R.id.tvPlayTimes)
        tvPlayTimes.text = "$randomNumber plays"

        // need to revise here
        val tvName = findViewById<EditText>(R.id.tvName)
        tvName.isEnabled = false;
        val ivAlbum = findViewById<ImageView>(R.id.ivAlbum)
        val tvSong = findViewById<TextView>(R.id.tvSong)
        val singer = findViewById<TextView>(R.id.singer)

        ivAlbum.setImageResource(albumSrc)
        tvSong.text = songName
        singer.text = singerName


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


