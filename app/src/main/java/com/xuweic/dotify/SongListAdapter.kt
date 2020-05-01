package com.xuweic.dotify

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnLongClickListener
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ericchee.songdataprovider.Song
import com.xuweic.dotify.fragment.SongListFragment

class SongAdaptor(listOfSongs: List<Song>, val context: Context): RecyclerView.Adapter<SongAdaptor.SongViewHolder>() {

    private var listOfSongs: MutableList<Song> = listOfSongs.toList() as MutableList<Song>

    lateinit var onSongClickListener: (song: Song) -> Unit

    lateinit var onSongLongClickListener: (position: Int) -> Unit

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.source, parent, false)
        return SongViewHolder(view)
    }

    override fun getItemCount(): Int = listOfSongs.size

    override fun onBindViewHolder(holder: SongViewHolder, position: Int) {
        holder.bind(listOfSongs[position], position)
    }

    fun change(newSongs: MutableList<Song>) {
        listOfSongs = newSongs
        notifyDataSetChanged()
        //val callback = SongDiffCallback(listOfSongs, newSongs)
        //val diffResult = DiffUtil.calculateDiff(callback)
        //diffResult.dispatchUpdatesTo(this)
        //listOfSongs = newSongs
    }

    inner class SongViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val nameSinger by lazy { itemView.findViewById<TextView>(R.id.nameSinger)}
        private val nameSong by lazy {itemView.findViewById<TextView>(R.id.songTitle)}
        private val imgAlbum by lazy {itemView.findViewById<ImageView>(R.id.imgHolder)}

        @SuppressLint("SetTextI18n")
        fun bind(song: Song, position: Int) {
            nameSinger.text = song.artist
            nameSong.text = song.title
            imgAlbum.setImageResource(song.smallImageID)

            itemView.setOnClickListener {
                onSongClickListener?.invoke(song)
            }

            itemView.setOnLongClickListener(OnLongClickListener {
                onSongLongClickListener?.invoke(position)
                listOfSongs.removeAt(position)
                notifyItemRemoved(adapterPosition)
                false
            })
        }
    }
}