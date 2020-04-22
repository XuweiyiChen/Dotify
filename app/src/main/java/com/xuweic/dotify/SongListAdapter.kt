package com.xuweic.dotify

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import com.ericchee.songdataprovider.Song

class SongAdaptor(listOfSongs: List<Song>, val context: Context): RecyclerView.Adapter<SongAdaptor.SongViewHolder>() {

    private var listOfSongs: List<Song> = listOfSongs.toList()

    lateinit var onPersonClickListener: (song: Song) -> Unit

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.source, parent, false)
        return SongViewHolder(view)
    }

    override fun getItemCount(): Int = listOfSongs.size

    override fun onBindViewHolder(holder: SongViewHolder, position: Int) {
//        val singerNames = listOfSongs[position].artist
//        val songNames = listOfSongs[position].title
//        val albumImg = listOfSongs[position].smallImageID
        //singerNames, songNames, albumImg
        holder.bind(listOfSongs[position])
    }

    fun change(newSongs: List<Song>) {
//        val callback = SongDiffCallback(listOfSongs, newSongs)
//        val diffResult = DiffUtil.calculateDiff(callback)
//        diffResult.dispatchUpdatesTo(this)
        listOfSongs = newSongs
        notifyDataSetChanged()
    }

    inner class SongViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val nameSinger by lazy { itemView.findViewById<TextView>(R.id.nameSinger)}
        private val nameSong by lazy {itemView.findViewById<TextView>(R.id.songTitle)}
        private val imgAlbum by lazy {itemView.findViewById<ImageView>(R.id.imgHolder)}

        @SuppressLint("SetTextI18n")
        //singerNames: String, songNames: String, albumImg: Int
        fun bind(song: Song) {
            nameSinger.text = song.artist
            nameSong.text = song.title
            imgAlbum.setImageResource(song.smallImageID)

            itemView.setOnClickListener {
                //Toast.makeText(context, "This is my name $singerNames", Toast.LENGTH_SHORT).show()
                //tvBrief.text = "$songNames-$singerNames"
                onPersonClickListener?.invoke(song)
            }
        }
    }

}