package com.sofiamarchinskya.cleanarchapi.presentation.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sofiamarchinskya.cleanarchapi.R
import com.sofiamarchinskya.cleanarchapi.databinding.ListActivityBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ListActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ListActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(findViewById(R.id.toolbar))
    }
}
