package com.example.myimcv3


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myimcv3.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    //lateinit var tabLayout: TabLayout
    //lateinit var viewPager: ViewPager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        // Se activan las tabs que se van a mostrar
        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(ImcFragment(), "IMC")
        adapter.addFragment(HistoricoFragment(), "Histórico")

        // Se asocia el adapter.
        binding.viewPager.adapter = adapter

        // Se cargan las tabs.
        binding.tabs.setupWithViewPager(binding.viewPager)
    }


}
