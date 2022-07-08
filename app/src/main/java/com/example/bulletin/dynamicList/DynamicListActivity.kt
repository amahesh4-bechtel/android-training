package com.example.bulletin.dynamicList

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.bulletin.R
import com.squareup.picasso.Picasso


class DynamicListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        val headingView: TextView = findViewById(R.id.heading)
        val imageView: ImageView = findViewById(R.id.image)
        val newsView: TextView = findViewById(R.id.news)
        val dateView: TextView = findViewById(R.id.date)

        val bundle: Bundle?= intent.extras

        val heading = bundle!!.getString("heading")
        val imageSrc = bundle!!.getString("imageSrc")
        val news = bundle!!.getString("news")
        val date = bundle!!.getString("date")

        headingView.text = heading
        if (imageSrc != null) {
            Picasso.get().load(imageSrc).into(imageView);

        }
        newsView.text = news
        dateView.text = date
    }
}