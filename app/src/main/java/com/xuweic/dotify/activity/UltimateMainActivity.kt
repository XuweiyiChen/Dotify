package com.xuweic.dotify.activity

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import com.ericchee.songdataprovider.Song
import com.ericchee.songdataprovider.SongDataProvider
import com.xuweic.dotify.R
import com.xuweic.dotify.fragment.NowPlayingFragment
import com.xuweic.dotify.fragment.OnEmailSelectedListener
import com.xuweic.dotify.fragment.SongListFragment
import kotlin.properties.Delegates

class UltimateMainActivity : AppCompatActivity(), OnEmailSelectedListener {

    private lateinit var tvBrief: TextView
    private lateinit var btShuffle: Button
    private lateinit var singerName: String
    private lateinit var songName: String
    private lateinit var line: LinearLayout
    private lateinit var tvPlayTimes: TextView
    private var playTimes by Delegates.notNull<Int>()

    private var currentSong: Song? = null;

    companion object {
        private const val OUT_SONG = "out_song"
        private const val OUT_BOOLEAN = "out_boolean"
        private const val OUT_STRING = "out_string"
    }

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ultimate__main_)

        title = "All Songs"


        val songListFragment = SongListFragment()
        val allSongs: MutableList<Song> = SongDataProvider.getAllSongs() as MutableList<Song>


        val argumentBundle = Bundle()
        argumentBundle.putParcelableArray(SongListFragment.SONG_LIST, allSongs.toTypedArray());
        songListFragment.arguments = argumentBundle


        songListFragment.arguments = argumentBundle


        supportFragmentManager
            .beginTransaction()
            .add(R.id.fragContainer, songListFragment, SongListFragment.TAG)
            .commit()

        supportFragmentManager.addOnBackStackChangedListener {
            val hasBackStack = supportFragmentManager.backStackEntryCount > 0
            if (hasBackStack) {
                supportActionBar?.setDisplayHomeAsUpEnabled(true)
            } else {
                supportActionBar?.setDisplayHomeAsUpEnabled(false)
            }
        }


        btShuffle = findViewById(R.id.btShuffle)

        btShuffle.setOnClickListener {
            val newSongs = allSongs.shuffled()
            getSongListFragment()?.updateShuffle(newSongs)
        }


        val songDetailFragment = NowPlayingFragment()

        line = findViewById(R.id.line)

//        val songListFragmentSave = getSongListFragment()
//        val nowPlayingFragmentSave = getSongDetailFragment()
        tvBrief = findViewById(R.id.tvBrief)
        //tvBrief.text = ""
        if (savedInstanceState != null) {
            with(savedInstanceState) {
                currentSong = getParcelable(OUT_SONG)
                var state = getBoolean(OUT_BOOLEAN)
                if (state) {
                    if (currentSong != null) {
                        tvBrief.text = currentSong!!.artist + " - " + currentSong!!.title
                    } else {
                        tvBrief.text = ""
                    }
                } else {
                    playTimes = getInt(OUT_STRING)
                    Log.i("SHENTIANYI1", playTimes.toString())
                    line.isVisible = false
                    val argumentSong = Bundle()
                    argumentSong.putParcelable(NowPlayingFragment.SONG_DETAILS, currentSong)
                    argumentSong.putInt(NowPlayingFragment.PLAY_TIMES, playTimes)
                    songDetailFragment.arguments = argumentSong

                    supportFragmentManager
                        .beginTransaction()
                        .add(R.id.fragContainer, songDetailFragment, NowPlayingFragment.TAG)
                        .addToBackStack(NowPlayingFragment.TAG)
                        .commit()
                }
            }
        } else {
            if (line.isVisible) {
                tvBrief.text = ""
            }
            playTimes = 0
        }

        if (getSongDetailFragment() == null){
            line.setOnClickListener {
                if (currentSong != null) {
                    line.isVisible = false
                    val argumentSong = Bundle()
                    argumentSong.putParcelable(NowPlayingFragment.SONG_DETAILS, currentSong)
                    argumentSong.putInt(NowPlayingFragment.PLAY_TIMES, playTimes)
                    songDetailFragment.arguments = argumentSong

                    supportFragmentManager
                        .beginTransaction()
                        .add(R.id.fragContainer, songDetailFragment, NowPlayingFragment.TAG)
                        .addToBackStack(NowPlayingFragment.TAG)
                        .commit()
                }
            }
        } else {
            line.setOnClickListener {
                if (currentSong != null) {
                    currentSong?.let { getSongDetailFragment()!!.updateSong(it, playTimes) }
                }
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putParcelable(OUT_SONG, currentSong)
        outState.putBoolean(OUT_BOOLEAN, line.isVisible)

        if (!line.isVisible) {
            tvPlayTimes = findViewById(R.id.tvPlayTimes)
            val input = tvPlayTimes.text.split(" ")
            Log.i("SHENTIANYI", input[0])
            outState.putInt(OUT_STRING, input[0].toInt())
        }

        super.onSaveInstanceState(outState)
    }

    override fun onSupportNavigateUp(): Boolean {
        supportFragmentManager.popBackStack()
        line.isVisible = true
        return super.onNavigateUp()
    }

    private fun getSongListFragment() = supportFragmentManager.findFragmentByTag(SongListFragment.TAG) as? SongListFragment
    private fun getSongDetailFragment() = supportFragmentManager.findFragmentByTag(NowPlayingFragment.TAG) as? NowPlayingFragment

    @SuppressLint("SetTextI18n")
    override fun onSongSelected(song: Song) {
        //val tvBrief = findViewById<TextView>(R.id.tvBrief)
        singerName = song.artist
        songName = song.title
        tvBrief.text = song.artist + " - " + song.title
        currentSong = song
    }
}

