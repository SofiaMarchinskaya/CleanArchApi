package com.sofiamarchinskya.cleanarchapi.presentation.view

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.viewpager2.widget.ViewPager2
import com.sofiamarchinskya.cleanarchapi.R
import com.sofiamarchinskya.cleanarchapi.databinding.ActivityMainBinding
import com.sofiamarchinskya.cleanarchapi.presentation.view.adapter.StarWarsPagerAdapter
import com.sofiamarchinskya.cleanarchapi.utils.Constants

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var pagerAdapter: StarWarsPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        pagerAdapter = StarWarsPagerAdapter(this)
        binding.apply {
            pager.adapter = pagerAdapter
            bottomNavigation.setOnItemSelectedListener { item ->
                pager.currentItem = item.order
                true
            }
            pager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    bottomNavigation.menu.getItem(position).isChecked = true
                }
            })
        }
    }
}