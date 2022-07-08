package com.example.bulletin.staticList

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.example.bulletin.R

class StaticListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        val headingView: TextView = findViewById(R.id.heading)
        val imageView: ImageView = findViewById(R.id.image)
        val newsView: TextView = findViewById(R.id.news)
        val dateView: TextView = findViewById(R.id.date)

        val bundle: Bundle?= intent.extras

        val heading = bundle!!.getString("heading")
        val imageId = bundle!!.getInt("imageId")
        val news = bundle!!.getString("news")
        val date = bundle!!.getString("date")

        headingView.text = heading
        imageView.setImageResource(imageId)
        newsView.text = news
        dateView.text = date
    }
}