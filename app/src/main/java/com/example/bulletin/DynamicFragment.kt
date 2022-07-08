package com.example.bulletin


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bulletin.databinding.FragmentDynamicBinding
import com.example.bulletin.dynamicList.DynamicListActivity
import com.example.bulletin.dynamicList.DynamicListAdapter
import com.example.bulletin.dynamicList.DynamicListClass
import com.example.bulletin.network.ApiInterface
import com.example.bulletin.network.JsonDataModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.StringBuilder

const val BASE_URL = "https://newsapi.org/v2/"
class DynamicFragment : Fragment() {

    private var _binding: FragmentDynamicBinding? = null
    private lateinit var newRecyclerView: RecyclerView
    private lateinit var newArrayList: ArrayList<DynamicListClass>
    lateinit var imageId: Array<Int>
    lateinit var heading: Array<String>
    lateinit var date: Array<String>
    lateinit var news: Array<String>
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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

        getNewsData()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentDynamicBinding.inflate(inflater, container, false)
        newRecyclerView = binding.dynamicRecyclerView
        newRecyclerView.layoutManager = LinearLayoutManager(this.context)
        newRecyclerView.setHasFixedSize(true)
        newArrayList = arrayListOf<DynamicListClass>()
        return binding.root
    }

    private fun getNewsData() {
        val retrofitBuilder = Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL).build().create(ApiInterface::class.java)

        val retrofitData = retrofitBuilder.getData()

        retrofitData.enqueue(object : Callback<JsonDataModel> {
            override fun onResponse(
                call: Call<JsonDataModel>,
                response: Response<JsonDataModel>
            ) {
                val responseBody = response.body()!!
                //val myStringBuilder = StringBuilder()
                getData(responseBody)
//                for (myData in responseBody) {
//                    myStringBuilder.append(myData.body)
//                    myStringBuilder.append("/n")
//                }
//                binding.dynamicTextView.text = myStringBuilder
            }

            override fun onFailure(call: Call<JsonDataModel>, t: Throwable) {
                Log.d("DynaminActivity", "onFailure: "+t.message)
            }
        })
    }
    private fun getData(newsInput: JsonDataModel) {
        for (myData in newsInput.articles) {
            val item = DynamicListClass(myData.urlToImage, myData.title, myData.publishedAt, myData.content)
            newArrayList.add(item)
        }
        var adapter = DynamicListAdapter(newArrayList)
        newRecyclerView.adapter = adapter
        adapter.setOnItemClickListener(object: DynamicListAdapter.onItemClickListener{
            override fun onItemClick(position: Int) {
                //Toast.makeText(context, "Clicked on item no.", Toast.LENGTH_SHORT).show()
                val intent = Intent(context, DynamicListActivity::class.java)
                intent.putExtra("heading", newArrayList[position].heading)
                intent.putExtra("imageSrc", newArrayList[position].titleImage)
                intent.putExtra("news", newArrayList[position].news)
                intent.putExtra("date", newArrayList[position].date)
                startActivity(intent)
            }

        })
    }
}