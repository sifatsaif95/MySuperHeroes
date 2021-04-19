package com.britishbroadcast.mysuperheroes.model.network

import com.britishbroadcast.mysuperheroes.model.data.HeroesResponse
import com.britishbroadcast.mysuperheroes.util.Constants.Companion.API_PATH
import io.reactivex.Single
import retrofit2.http.GET

interface HeroesService {
    @GET(API_PATH)
    fun getHeroes(): Single<HeroesResponse>
}