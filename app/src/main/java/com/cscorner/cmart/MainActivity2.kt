package com.cscorner.cmart

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.cscorner.cmart.databinding.ActivityMain2Binding
import okhttp3.MediaType
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity2 : AppCompatActivity() {
    private lateinit var binding: ActivityMain2Binding
    private lateinit var Adapter: myadapter
    private lateinit var List: List<Product>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding=ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        setrecyclerview()

        binding.imageView8.setOnClickListener {
            startActivity(Intent(this, MainActivity4::class.java))
        }
        binding.imageView4.setOnClickListener {
            startActivity(Intent(this, MainActivity3::class.java))
        }
        binding.imageView5.setOnClickListener {
            startActivity(Intent(this, MainActivity5::class.java))
        }

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

                List = responseBody.products
                Adapter = myadapter(this@MainActivity2, List)
                binding.recycler.adapter = Adapter
                binding.recycler.layoutManager = LinearLayoutManager(this@MainActivity2, LinearLayoutManager.VERTICAL, false)


            }

            override fun onFailure(p0: Call<DATA?>, p1: Throwable) {
                Log.d("Main Activity", "onFailure: " + p1.message)
            }
        })
    }


}