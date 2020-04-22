package com.xuweic.dotify

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.widget.TextView
import android.widget.Toast
import com.ericchee.songdataprovider.Song
import com.ericchee.songdataprovider.SongDataProvider
import com.xuweic.dotify.MainActivity.Companion.NAME_KEY
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_recyclerview.*
import kotlin.properties.Delegates

class SongListActivity : AppCompatActivity() {

    private lateinit var tvBrief: TextView
    private lateinit var singerName: String
    private lateinit var songName: String
    private var albumAddress by Delegates.notNull<Int>()

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recyclerview)

        title = "All Songs"

        this.tvBrief = findViewById(R.id.tvBrief)

        val allSongs: List<Song> = SongDataProvider.getAllSongs()

        tvBrief.text = ""


        val songAdaptor = SongAdaptor(allSongs, this)


        songAdaptor.onPersonClickListener = {song ->
            singerName = song.artist
            songName = song.title
            albumAddress = song.largeImageID
            tvBrief.text = song.artist + " - " + song.title
        }


        rvSongs.adapter = songAdaptor

        btShuffle.setOnClickListener {
            val newSongs = allSongs.shuffled()
            songAdaptor.change(newSongs)
        }

        line.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("SONG_KEY", singerName)
            intent.putExtra("ALBUM_KEY", albumAddress)
            intent.putExtra("SONG_KEY", songName)
            startActivity(intent)
        }
    }

}


