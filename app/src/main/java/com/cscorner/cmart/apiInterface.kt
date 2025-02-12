package com.cscorner.cmart

import retrofit2.Call
import retrofit2.http.GET

 interface apiInterface {
    @GET("prodcuts")
    fun getProducts(): Call<DATA>
}