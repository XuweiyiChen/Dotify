package com.xuweic.dotify.fragment

import android.annotation.SuppressLint
import android.content.Context
import android.nfc.Tag
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.ericchee.songdataprovider.Song
import com.xuweic.dotify.R
import com.xuweic.dotify.SongAdaptor
import kotlin.properties.Delegates

class SongListFragment: Fragment() {

    companion object {
        val TAG = SongListFragment::class.java.simpleName

        const val SONG_LIST = "song list"
    }

    private lateinit var allSongs: List<Song>

    private var onEmailSelectedListener: OnEmailSelectedListener? = null
    private lateinit var songAdaptor: SongAdaptor

    override fun onAttach(context: Context?) {
        super.onAttach(context)

        if (context is OnEmailSelectedListener) {
            onEmailSelectedListener = context
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {args ->
            val temp = args.getParcelableArray(SONG_LIST)
            if (temp != null) {
                allSongs = temp.toList().map { it as Song }
            }
        }

    }

    fun updateShuffle(newSongs: List<Song>) {
        songAdaptor.change(newSongs as MutableList<Song>)
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return layoutInflater.inflate(R.layout.activity_recyclerview, container, false)
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val rvSongs = view.findViewById<RecyclerView>(R.id.rvSongs)

        songAdaptor = context?.let { SongAdaptor(allSongs, it) }!!
        rvSongs.adapter = songAdaptor

        if (songAdaptor != null) {
            songAdaptor.onSongClickListener = {song ->
                onEmailSelectedListener?.onSongSelected(song)
            }
        }
    }
}

interface OnEmailSelectedListener {
    fun onSongSelected(song: Song)
}