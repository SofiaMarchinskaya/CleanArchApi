package com.sofiamarchinskya.cleanarchapi.presentation.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.sofiamarchinskya.cleanarchapi.databinding.ActivityMainBinding
import com.sofiamarchinskya.cleanarchapi.presentation.view.adapter.StarWarsPagerAdapter

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