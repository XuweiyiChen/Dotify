package com.xuweic.dotify.activity

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.ericchee.songdataprovider.Song
import com.ericchee.songdataprovider.SongDataProvider
import com.xuweic.dotify.R
import com.xuweic.dotify.SongAdaptor
import kotlinx.android.synthetic.main.activity_recyclerview.*
import kotlin.properties.Delegates

class SongListActivity : AppCompatActivity() {

    private lateinit var tvBrief: TextView
    private lateinit var singerName: String
    private lateinit var songName: String
    private var albumAddress by Delegates.notNull<Int>()
    private var currentSong: Song? = null;

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recyclerview)

        title = "All Songs"

        this.tvBrief = findViewById(R.id.tvBrief)

        val allSongs: MutableList<Song> = SongDataProvider.getAllSongs() as MutableList<Song>

        tvBrief.text = ""

        val songAdaptor = SongAdaptor(allSongs,this)

        songAdaptor.onSongClickListener = {song ->
            singerName = song.artist
            songName = song.title
            albumAddress = song.largeImageID
            tvBrief.text = song.artist + " - " + song.title
            currentSong = song
        }

        songAdaptor.onSongLongClickListener = {position ->
            val songNames = allSongs[position].title
//            allSongs.drop(position)
            //Toast.makeText(this, "Remove $songNames", Toast.LENGTH_SHORT).show()
        }

        rvSongs.adapter = songAdaptor

//        btShuffle.setOnClickListener {
//            val newSongs = allSongs.shuffled()
//            songAdaptor.change(newSongs as MutableList<Song>)
//        }

//        line.setOnClickListener {
////            val intent = Intent(this, MainActivity::class.java).apply {
////                putExtra(NAME_KEY, currentSong)
////            }
//            startActivity(intent) }
    }

}


