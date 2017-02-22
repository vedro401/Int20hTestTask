package com.example.someone.int20htesttask

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import java.util.Calendar
import java.text.SimpleDateFormat

class MainActivity : AppCompatActivity() {



    val results =  HashMap<String,String>()
    var count = 0;
    var fullCount = 0;
    lateinit var dateInterval :List<String>

    lateinit var retrofit: Retrofit
    lateinit var pbapi: PBApi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getButton.setOnClickListener {
//            getExchangeRateForDate(fromDate.text.toString())
            resultTextViev.text = ""
            count = 0
            dateInterval = takeDateInterval(fromDate.text.toString(),toDate.text.toString())
            fullCount = dateInterval.size
            for(date in dateInterval){
                getExchangeRateForDate(date)
            }
        }




    }

    fun getExchangeRateForDate(date: String) {

        var posts = ArrayList<ExchangeRate>()

        retrofit = Retrofit.Builder()
                .baseUrl("https://api.privatbank.ua/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        pbapi = retrofit!!.create(PBApi::class.java)

        pbapi.getData("", date).enqueue(object : Callback<PostModel2> {
            override fun onResponse(call: Call<PostModel2>?, response: Response<PostModel2>?) {
                posts.addAll(response!!.body().exchangeRate)
//                resultTextViev.text = "${resultTextViev.text} \n $date"
                for (post in posts) {
                    if(post.currency.equals("USD")) {
                        results.put(date, "${post.baseCurrency} | ${post.currency} : ${post.saleRateNB} | ${post.purchaseRateNB} " +
                                " PB: ${post.saleRate} | ${post.purchaseRate}")
                        break
                    }
                }
                if(results.get(date)==null)
                    results.put(date,"Bank does not provide information on this date")
                if(++count == fullCount)showAll()

            }
            override fun onFailure(call: Call<PostModel2>?, t: Throwable?) {
                count++;
                Toast.makeText(this@MainActivity, "An error occurred during networking", Toast.LENGTH_SHORT).show()
            }

        })

    }

    fun showAll(){
        for(date in dateInterval){
            resultTextViev.text = "${resultTextViev.text }  \n $date \n ${results.get(date)}"
        }
    }

}

