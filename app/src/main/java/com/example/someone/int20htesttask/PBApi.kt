package com.example.someone.int20htesttask

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by someone on 21.02.17.
 */
interface PBApi {
    @GET("p24api/exchange_rates")
    abstract fun getData(@Query("json") jsot: String, @Query("date") date: String): Call<PostModel2>
//    abstract fun getData(@Query("date") date: String,
//                         @Query("bank") bank: String,
//                         @Query("baseCurrency") baseCurrency: Int,
//                         @Query("baseCurrencyLit") baseCurrencyLit: String): Call<List<PostModel>>
}
