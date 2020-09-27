package com.example.podcastapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.podcastapp.R
import com.example.podcastapp.fragments.DownloadFragment
import com.example.podcastapp.fragments.HomeFragment
import com.example.podcastapp.fragments.ProfileFragment
import com.example.podcastapp.fragments.SearchFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        openFragment(HomeFragment())
        bottomNavigation.setOnNavigationItemSelectedListener {menuItem ->
            when(menuItem.itemId){
                R.id.action_home -> {
                    openFragment(HomeFragment())
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.action_search -> {
                    openFragment(SearchFragment())
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.action_download -> {
                    openFragment(DownloadFragment())
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.action_profile -> {
                    openFragment(ProfileFragment())
                    return@setOnNavigationItemSelectedListener true
                }
            }
            return@setOnNavigationItemSelectedListener false
        }
    }

    fun openFragment(fragment:Fragment){
        supportFragmentManager.beginTransaction().replace(R.id.flContainer, fragment).commit()
    }
}