package com.britishbroadcast.mysuperheroes.view.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.britishbroadcast.mysuperheroes.R
import com.britishbroadcast.mysuperheroes.view.fragment.HomeFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val homeFragment = HomeFragment()

        supportFragmentManager.beginTransaction()
            .setCustomAnimations(
                android.R.anim.fade_in,
                android.R.anim.fade_out,
                android.R.anim.fade_in,
                android.R.anim.fade_out
            ).replace(R.id.main_frameLayout, homeFragment)
            .addToBackStack(homeFragment.tag)
            .commit()
    }
}