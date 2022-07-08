package com.example.bulletin.dynamicList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bulletin.R
import com.google.android.material.imageview.ShapeableImageView
import com.squareup.picasso.Picasso

class DynamicListAdapter(private val itemList: ArrayList<DynamicListClass>) : RecyclerView.Adapter<DynamicListAdapter.ListViewHolder>()  {

    private lateinit var mListener: onItemClickListener

    interface onItemClickListener {
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: onItemClickListener) {
        mListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return ListViewHolder(itemView, mListener)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val currentItem = itemList[position]
        holder.tvHeading.text = currentItem.heading
        holder.tvDate.text = currentItem.date
        Picasso.get().load(currentItem.titleImage).into(holder.titleImage);
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    class ListViewHolder(itemView: View, listener: onItemClickListener) : RecyclerView.ViewHolder(itemView) {

        val titleImage: ShapeableImageView = itemView.findViewById(R.id.title_image)
        val tvHeading: TextView = itemView.findViewById(R.id.tv_heading)
        val tvDate: TextView = itemView.findViewById(R.id.tv_date)

        init {
            itemView.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }
        }

    }

}