package com.jomibusa.pruebaceiba.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.jomibusa.pruebaceiba.base.BaseActivity
import com.jomibusa.pruebaceiba.databinding.ActivityMainBinding

class MainActivity : BaseActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



    }
}