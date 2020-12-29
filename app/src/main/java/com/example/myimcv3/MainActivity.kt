package com.example.myimcv3


import androidx.appcompat.app.AppCompatActivity

import android.os.Bundle

import androidx.viewpager.widget.ViewPager
import com.example.myimcv3.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayout


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    lateinit var tabLayout: TabLayout
    lateinit var viewPager: ViewPager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(ImcFragment(), "IMC")
        adapter.addFragment(HistoricoFragment(), "Histórico")


        // Se asocia el adapter.
        binding.viewPager.adapter = adapter

        // Se cargan las tabs.
        binding.tabs.setupWithViewPager(binding.viewPager)
    }


}