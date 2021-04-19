package com.britishbroadcast.mysuperheroes.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.britishbroadcast.mysuperheroes.R
import com.britishbroadcast.mysuperheroes.databinding.HomeFragmentBinding
import com.britishbroadcast.mysuperheroes.model.data.Heroe
import com.britishbroadcast.mysuperheroes.view.adapter.HeroesItemAdapter
import com.britishbroadcast.mysuperheroes.viewmodel.HeroesViewModel

class HomeFragment: Fragment(), HeroesItemAdapter.HeroesDelegate {

    private val heroesViewModel by activityViewModels<HeroesViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = HomeFragmentBinding.inflate(inflater, container, false).also {

    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = HomeFragmentBinding.bind(view)
        val heroesItemAdapter = HeroesItemAdapter(this)

        heroesViewModel.getHeroes()

        heroesViewModel.heroesLiveData.observe(viewLifecycleOwner){
            binding.heroesRecyclerView.adapter = heroesItemAdapter
            heroesItemAdapter.updateHeroes(it)
        }
    }

    override fun showDetails(heroe: Heroe) {
        val detailsFragment = DetailsFragment()
        parentFragmentManager.beginTransaction()
            .setCustomAnimations(
                android.R.anim.fade_in,
                android.R.anim.fade_out,
                android.R.anim.fade_in,
                android.R.anim.fade_out
            )
            .replace(R.id.main_frameLayout, detailsFragment)
            .addToBackStack(detailsFragment.tag)
            .commit()
        heroesViewModel.heroesDetailsLiveData.postValue(heroe)
    }

}