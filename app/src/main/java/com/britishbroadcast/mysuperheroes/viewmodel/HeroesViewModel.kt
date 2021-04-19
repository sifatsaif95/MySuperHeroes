package com.britishbroadcast.mysuperheroes.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.britishbroadcast.mysuperheroes.model.data.Heroe
import com.britishbroadcast.mysuperheroes.model.data.HeroesResponse
import com.britishbroadcast.mysuperheroes.model.network.HeroesRetrofit
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class HeroesViewModel(application: Application): AndroidViewModel(application) {

    private val retrofit = HeroesRetrofit()
    private val compositeDisposable = CompositeDisposable()

    val heroesLiveData = MutableLiveData<HeroesResponse>()
    val heroesDetailsLiveData = MutableLiveData<Heroe>()

    fun getHeroes(){
        retrofit.getHeroes()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({
                       heroesLiveData.postValue(it)
            },{
                Log.d("TAG_ERROR", it.localizedMessage)
            })
    }

}