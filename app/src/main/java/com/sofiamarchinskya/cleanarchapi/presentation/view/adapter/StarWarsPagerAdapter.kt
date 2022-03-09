package com.sofiamarchinskya.cleanarchapi.presentation.view.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.sofiamarchinskya.cleanarchapi.presentation.view.FavouriteFragment
import com.sofiamarchinskya.cleanarchapi.presentation.view.MainListFragment

class StarWarsPagerAdapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment =
        if (position == 0) {
            MainListFragment()
        } else {
            FavouriteFragment()
        }
}
