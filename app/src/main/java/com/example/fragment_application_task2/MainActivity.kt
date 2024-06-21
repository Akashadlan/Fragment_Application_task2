package com.example.fragment_application_task2

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.fragment_application_task2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    var binding : ActivityMainBinding? = null
    var activityInterface : ActivityInterface? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding?.btnchangeFragment?.setOnClickListener {
            if (binding?.etentervalue?.text?.toString()?.isNullOrEmpty() == true) {
                binding?.etentervalue?.error = resources.getString(R.string.values)
            } else {
                activityInterface?.changeFragmentText("${binding?.etentervalue?.text?.toString()}")
            }
        }
    }
    fun changeActivity(string : String) {
        binding?.btnchangeFragment?.setText(string.toString())
    }
}