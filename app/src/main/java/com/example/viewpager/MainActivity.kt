package com.example.viewpager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.viewpager.widget.ViewPager
import com.example.viewpager.Adapter.Adapter
import com.example.viewpager.Utils.MyData
import com.example.viewpager.databinding.ActivityMainBinding
import com.example.viewpager.databinding.ItemTochkaBinding
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private lateinit var adapter: Adapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        MyData.addUser()

        adapter = Adapter(MyData.list)
        binding.viewPager.adapter = adapter
        binding.tabLayout.setupWithViewPager(binding.viewPager)

        setMyTabLayout()
    }

    private fun setMyTabLayout() {
        val count = binding.tabLayout.tabCount
        for (i in 0 .. count){
            val item = ItemTochkaBinding.inflate(layoutInflater)
            val tab = binding.tabLayout.getTabAt(i)
            tab?.customView = item.root
            tab?.view?.findViewById<LinearLayout>(R.id.tab_indicator)?.alpha = 0.1F
        }

        binding.keyingisi.setOnClickListener {
            val nextPage = binding.viewPager.currentItem + 1
            binding.viewPager.currentItem = nextPage
        }

        binding.skip.setOnClickListener {
            val lastPageIndex = adapter.count - 1
            binding.viewPager.setCurrentItem(lastPageIndex, true)
        }

        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                tab?.view?.findViewById<LinearLayout>(R.id.tab_indicator)?.alpha = 1F
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                tab?.view?.findViewById<LinearLayout>(R.id.tab_indicator)?.alpha = 0.1F
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })
    }
}