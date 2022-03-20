package com.sofiamarchinskya.cleanarchapi.presentation.view

import android.app.Activity
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
// Keys for navigation
const val DELETE_RESULT_OK = Activity.RESULT_FIRST_USER + 1
const val EDIT_RESULT_OK = Activity.RESULT_FIRST_USER + 2
