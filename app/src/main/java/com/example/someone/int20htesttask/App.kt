package com.example.someone.int20htesttask

import android.app.Application
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by someone on 21.02.17.
 */

class App : Application() {
    lateinit var retrofit: Retrofit
    lateinit var pbapi: PBApi

    override fun onCreate() {
        super.onCreate()

        retrofit = Retrofit.Builder()
                .baseUrl("https://privatbank.ua/") //Базовая часть адреса
                .addConverterFactory(GsonConverterFactory.create()) //Конвертер, необходимый для преобразования JSON'а в объекты
                .build()
        pbapi = retrofit!!.create(PBApi::class.java)
    }




}
