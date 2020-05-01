package com.xuweic.dotify.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.ericchee.songdataprovider.Song

import com.xuweic.dotify.R
import kotlin.properties.Delegates
import kotlin.random.Random

/**
 * A simple [Fragment] subclass.
 */
class NowPlayingFragment : Fragment() {

    companion object {
        val TAG = NowPlayingFragment::class.java.simpleName

        const val SONG_DETAILS = "song details"

        const val PLAY_TIMES = "play_times"
    }

    private lateinit var song: Song
    private var playTimes by Delegates.notNull<Int>()

    private lateinit var ivAlbum: ImageView
    private lateinit var tvSong: TextView
    private lateinit var singer: TextView
    private lateinit var btPlay: ImageButton
    private lateinit var btNext: ImageButton
    private lateinit var tvPlayTimes: TextView
    private lateinit var btPrevious: ImageButton
    private val randomNumber = Random.nextInt(0, 999999999)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i("SHENTIANYI3", "?????")

        arguments?.let {args ->
            song = args.getParcelable(NowPlayingFragment.SONG_DETAILS)!!
            playTimes = args.getInt(NowPlayingFragment.PLAY_TIMES, -1)
            Log.i("SHENTIANYI2", playTimes.toString())
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.activity_main, container, false)
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.i("SHENTIANYI4", playTimes.toString())

        updateSong(song, playTimes)

        btPlay = view.findViewById(R.id.btPlay)

        btPlay.setOnClickListener {
            val input = tvPlayTimes.text.split(" ")
            val output = input[0].toInt() + 1
            tvPlayTimes.text = "$output plays"
        }

        btNext =view.findViewById(R.id.btNext)

        btNext.setOnClickListener{
            Toast.makeText(context, "Skipping to next track", Toast.LENGTH_SHORT).show()
        }

        btPrevious = view.findViewById(R.id.btPrevious)

        btPrevious.setOnClickListener {
            Toast.makeText(context, "Skipping to previous track", Toast.LENGTH_SHORT).show()
        }
    }

    @SuppressLint("SetTextI18n")
    fun updateSong(song: Song, playTimes: Int) {

        Log.i("SHENTIANYI5", playTimes.toString())

        tvPlayTimes = view!!.findViewById(R.id.tvPlayTimes)
        ivAlbum = view!!.findViewById(R.id.ivAlbum)
        tvSong = view!!.findViewById(R.id.tvSong)
        singer = view!!.findViewById(R.id.singer)

        Log.i("SHENTIANYI", playTimes.toString())

        if (playTimes == 0) {
            tvPlayTimes.text = "$randomNumber plays"
        } else {
            Log.i("SHENGTIANYI", playTimes.toString())
            tvPlayTimes.text = "$playTimes plays"
        }

        ivAlbum.setImageResource(song.largeImageID)
        tvSong.text = song.title
        singer.text = song.artist
    }

}
