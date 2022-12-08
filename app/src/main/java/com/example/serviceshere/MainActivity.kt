package com.example.serviceshere

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import com.example.serviceshere.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //setContentView(R.layout.activity_main)
        binding.lblRegistrarse.setOnClickListener{
            binding.lblRegistrarse.background = resources.getDrawable(R.drawable.switch_trcks,null)
            binding.lblRegistrarse.setTextColor(resources.getColor(R.color.textColor, null))
            binding.lblEntrar.background = null
            binding.registrolayout.visibility = View.VISIBLE
            binding.loginlayout.visibility = View.GONE
            binding.lblEntrar.setTextColor(resources.getColor(R.color.bgColorLogin, null))
        }
        binding.lblEntrar.setOnClickListener{
            binding.lblRegistrarse.background = null
            binding.lblRegistrarse.setTextColor(resources.getColor(R.color.bgColorLogin, null))
            binding.lblEntrar.background = resources.getDrawable(R.drawable.switch_trcks, null)
            binding.registrolayout.visibility = View.GONE
            binding.loginlayout.visibility = View.VISIBLE
            binding.lblEntrar.setTextColor(resources.getColor(R.color.textColor, null))
        }

    }
}