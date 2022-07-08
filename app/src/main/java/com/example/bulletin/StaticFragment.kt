package com.example.bulletin

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bulletin.databinding.FragmentStaticBinding
import com.example.bulletin.staticList.StaticListActivity
import com.example.bulletin.staticList.StaticListAdapter
import com.example.bulletin.staticList.StaticListClass


class StaticFragment : Fragment() {

    private var _binding: FragmentStaticBinding? = null
    private lateinit var newRecyclerView: RecyclerView
    private lateinit var newArrayList: ArrayList<StaticListClass>
    lateinit var imageId: Array<Int>
    lateinit var heading: Array<String>
    lateinit var date: Array<String>
    lateinit var news: Array<String>

    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true);
//        (activity as AppCompatActivity).supportActionBar?.setDisplayShowHomeEnabled(true);

        imageId = arrayOf(
            R.drawable.a,
            R.drawable.b,
            R.drawable.c,
            R.drawable.d,
            R.drawable.e,
            R.drawable.f,
            R.drawable.g
        )

        heading = arrayOf(
            "FBI, MI5 chiefs raise alarms about Chinese espionage in rare joint address",
            "Boris Johnson digs in despite calls to quit",
            "Pak TV journalist arrested on outskirts of Islamabad",
            "China backs Pakistan in opposing G20 Kashmir meetings",
            "Russia claims key Ukraine city, shelling kills 6 in Sloviansk",
            "Several dead in Copenhagen mall shooting; suspect arrested",
            "North Korea slams U.S.-South Korea-Japan military cooperation"
        )

        date = arrayOf(
            "JULY 07, 2022 08:55 IST",
            "JULY 06, 2022 19:35 IST",
            "JULY 06, 2022 08:05 IST",
            "JULY 05, 2022 23:57 IST",
            "JULY 03, 2022 22:13 IST",
            "JULY 03, 2022 22:48 IST",
            "JULY 03, 2022 22:35 IST"
        )

        news = arrayOf(
            getString(R.string.a),
            getString(R.string.b),
            getString(R.string.c),
            getString(R.string.d),
            getString(R.string.e),
            getString(R.string.f),
            getString(R.string.g)
        )


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentStaticBinding.inflate(inflater, container, false)
        newRecyclerView = binding.staticRecyclerView
        newRecyclerView.layoutManager = LinearLayoutManager(this.context)
        newRecyclerView.setHasFixedSize(true)

        newArrayList = arrayListOf<StaticListClass>()
        getData()
        return binding.root
    }

    private fun getData() {
        for(i in imageId.indices) {
            val item = StaticListClass(imageId[i], heading[i], date[i])
            newArrayList.add(item)
        }
        var adapter = StaticListAdapter(newArrayList)
        newRecyclerView.adapter = adapter
        adapter.setOnItemClickListener(object: StaticListAdapter.onItemClickListener{
            override fun onItemClick(position: Int) {
                //Toast.makeText(context, "Clicked on item no.", Toast.LENGTH_SHORT).show()
                val intent = Intent(context, StaticListActivity::class.java)
                intent.putExtra("heading", newArrayList[position].heading)
                intent.putExtra("imageId", newArrayList[position].titleImage)
                intent.putExtra("news", news[position])
                intent.putExtra("date", newArrayList[position].date)
                startActivity(intent)
            }

        })
    }

}