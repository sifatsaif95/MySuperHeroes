package com.britishbroadcast.mysuperheroes.view.fragment

import android.app.Application
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.britishbroadcast.mysuperheroes.databinding.DetailsFragmentBinding
import com.britishbroadcast.mysuperheroes.databinding.HomeFragmentBinding
import com.britishbroadcast.mysuperheroes.util.Constants
import com.britishbroadcast.mysuperheroes.viewmodel.HeroesViewModel
import com.bumptech.glide.Glide

class DetailsFragment: Fragment() {

    private val heroesViewModel by activityViewModels<HeroesViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = DetailsFragmentBinding.inflate(inflater, container, false).also {

    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = DetailsFragmentBinding.bind(view)
        binding.apply {
            heroesViewModel.heroesDetailsLiveData.observe(viewLifecycleOwner) {
                val imageUrl = Constants.IMAGE_URL + it.Picture.split(".")[0] + ".jpg"
                Glide.with(heroeImageview)
                    .load(imageUrl)
                    .into(heroeImageview)

                titleTextview.text = it.Name
                scoreTextview.text = it.Score.toString()
            }
        }
    }
}