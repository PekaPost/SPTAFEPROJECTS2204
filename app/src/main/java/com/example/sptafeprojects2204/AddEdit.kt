package com.example.sptafeprojects2204

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.sptafeprojects2204.databinding.AddeditBinding

class AddEdit : AppCompatActivity() {
    private lateinit var binding: AddeditBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = AddeditBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}