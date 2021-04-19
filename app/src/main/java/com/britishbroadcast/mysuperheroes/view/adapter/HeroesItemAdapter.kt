package com.britishbroadcast.mysuperheroes.view.adapter

import android.view.LayoutInflater
import android.view.TouchDelegate
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.britishbroadcast.mysuperheroes.databinding.HeroesItemLayoutBinding
import com.britishbroadcast.mysuperheroes.model.data.Heroe
import com.britishbroadcast.mysuperheroes.model.data.HeroesResponse
import com.britishbroadcast.mysuperheroes.util.Constants.Companion.IMAGE_URL
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class HeroesItemAdapter(val delegate: HeroesDelegate): RecyclerView.Adapter<HeroesItemAdapter.HeroesItemViewHolder>() {

    inner class HeroesItemViewHolder(val binding: HeroesItemLayoutBinding): RecyclerView.ViewHolder(binding.root)

    interface HeroesDelegate{
        fun showDetails(heroe: Heroe)
    }

    private var heroes = HeroesResponse(mutableListOf())

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeroesItemViewHolder {
        var binding = HeroesItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HeroesItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HeroesItemViewHolder, position: Int) {
        holder.binding.apply {
            heroes.Heroes[position].let {
                val imageUrl = IMAGE_URL + it.Picture.split(".")[0] + ".jpg"
                Glide.with(heroeImageview)
                    .load(imageUrl)
                    .into(heroeImageview)

                titleTextview.text = it.Name
                ratingTextview.text = it.Score.toString()
            }
        }

        holder.itemView.setOnClickListener {
            delegate.showDetails(heroes.Heroes[position])
        }
    }

    override fun getItemCount(): Int = heroes.Heroes.size

    fun updateHeroes(heroes: HeroesResponse){
        this.heroes = heroes
        notifyDataSetChanged()
    }
}