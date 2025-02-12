package com.cscorner.cmart

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cscorner.cmart.databinding.ActivityMain2Binding
import com.cscorner.cmart.databinding.ActivityMain3Binding
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity3 : AppCompatActivity() {
    private lateinit var binding: ActivityMain3Binding
    private lateinit var Adapter: madap
    private lateinit var List2: MutableList<Product>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding=ActivityMain3Binding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        List2 = mutableListOf()
        setrecyclerview()
    }
    fun setrecyclerview()
    {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://dummyjson.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(apiInterface::class.java)
        val retrofitData = retrofit.getProducts()
        retrofitData.enqueue(object : retrofit2.Callback<DATA?> {
            override fun onResponse(p0: Call<DATA?>, p1: Response<DATA?>) {
                val responseBody = p1.body()!!
                for(i in responseBody.products.indices)
                {
                    if(responseBody.products[i].category == "groceries")
                    {
                        List2.add(responseBody.products[i])
                    }
                }

                Adapter = madap(this@MainActivity3, List2)
                binding.rv.adapter = Adapter
                binding.rv.layoutManager = LinearLayoutManager(this@MainActivity3, LinearLayoutManager.VERTICAL, false)


            }

            override fun onFailure(p0: Call<DATA?>, p1: Throwable) {
                Log.d("Main Activity", "onFailure: " + p1.message)
            }
        })
    }
}