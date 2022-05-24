package com.example.checkyourpulse.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.checkyourpulse.R
import com.example.checkyourpulse.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commit()
        }
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}