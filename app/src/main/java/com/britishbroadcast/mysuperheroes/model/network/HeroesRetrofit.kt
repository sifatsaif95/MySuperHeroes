package com.britishbroadcast.mysuperheroes.model.network

import com.britishbroadcast.mysuperheroes.model.data.HeroesResponse
import com.britishbroadcast.mysuperheroes.util.Constants.Companion.BASE_URL
import io.reactivex.Single
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class HeroesRetrofit {
    private val service: HeroesService

    init {
        service = createService(createRetrofit())
    }

    private fun createService(retrofit: Retrofit): HeroesService = retrofit.create(HeroesService::class.java)
    private fun createRetrofit(): Retrofit {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()


        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    fun getHeroes(): Single<HeroesResponse> = service.getHeroes()
}