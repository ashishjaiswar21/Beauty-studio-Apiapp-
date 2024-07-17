package com.example.apiapp

import MyData
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class MainActivity : AppCompatActivity() {

    // code
    lateinit var recyclerView : RecyclerView
    lateinit var myAdaptor: MyAdaptor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // write code
        val retrofitBuilder = Retrofit.Builder()
            .baseUrl("https://dummyjson.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(Apiinterface::class.java)

        val retrofitData = retrofitBuilder.getProductData()
        retrofitData.enqueue(object : Callback<MyData?> {
            // imported  2 members
            override fun onResponse(p0: Call<MyData?>, p1: Response<MyData?>) {
                // if api call is a succes ,then use data of api and show in your app
                val responseBody = p1.body()
                val productList = responseBody?.products!!

                myAdaptor = MyAdaptor(this@MainActivity,productList)
                recyclerView.adapter = myAdaptor
                recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)

//                val collectDataInSB = StringBuilder()
//
//                for(myData in productList){
//                    collectDataInSB.append(myData.title + " ")
//                }
//                val tv = findViewById<TextView>(R.id.textView)
//                tv.text = collectDataInSB
            }

            override fun onFailure(p0: Call<MyData?>, p1: Throwable) {
                // if api call fails
                Log.d("Main Activty","onFailure: "+ p1.message)

            }


        })

    }
}