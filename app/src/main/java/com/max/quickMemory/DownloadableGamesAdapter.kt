
package com.max.quickMemory

import android.content.ContentValues.TAG
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.max.quickMemory.models.BoardSize
import com.max.quickMemory.models.MemoryCard
import com.max.quickMemory.models.OnlineMemoryCard
import com.squareup.picasso.Picasso
import kotlin.math.min

class DownloadableGamesAdapter(
  private val context: Context,
  private val dataList:MutableList<OnlineMemoryCard>,
  private val cardClickListener: CardClickListener
) :
  RecyclerView.Adapter<DownloadableGamesAdapter.ViewHolder>() {

  interface CardClickListener {
    fun onCardClicked(position: Int,data:OnlineMemoryCard)
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

    val view = LayoutInflater.from(context).inflate(R.layout.available_game_item_layout, parent, false)
    return ViewHolder(view)
  }

  override fun getItemCount() = dataList.size

  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    holder.bind(position)
  }

  inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val name = itemView.findViewById<TextView>(R.id.gamename)
    private val preview = itemView.findViewById<ImageView>(R.id.preview)

    fun bind(position: Int) {
      val data = dataList[position]
      name.text = data.name
      Picasso.get().load(data.imageList.images?.get(0)).placeholder(R.drawable.ic_image).into(preview)
      name.setOnClickListener {
        Log.i(TAG, "Clicked on position $position")
        cardClickListener.onCardClicked(position,data)
      }
    }

  }
}
