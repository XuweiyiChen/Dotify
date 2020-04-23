package com.xuweic.dotify

import androidx.recyclerview.widget.DiffUtil
import com.ericchee.songdataprovider.Song

class SongDiffCallback(
    private val previousSongs: List<Song>,
    private val newSongs: List<Song>
): DiffUtil.Callback() {

    override fun getOldListSize(): Int = previousSongs.size

    override fun getNewListSize(): Int = newSongs.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val previousSong = previousSongs[oldItemPosition]
        val newSong = newSongs[newItemPosition]

        return previousSong.id == newSong.id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val previousSong = previousSongs[oldItemPosition]
        val newSong = newSongs[newItemPosition]
        
        return previousSong.artist == newSong.artist
    }
}