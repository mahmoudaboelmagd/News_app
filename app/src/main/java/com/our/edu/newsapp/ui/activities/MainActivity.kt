package com.our.edu.newsapp.ui.activities


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.ui.*
import com.google.android.material.navigation.NavigationView
import com.our.edu.newsapp.R
import com.our.edu.newsapp.databinding.ActivityMainBinding
import com.our.edu.newsapp.utils.Utilities
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity(){

    private lateinit var mainBinding:ActivityMainBinding

    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        navigationComponentHandleClicks()

    }

    private fun navigationComponentHandleClicks() {


        val navView: NavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home
            ), mainBinding.drawerLayout
        )

        val exploreItem = navView.menu.findItem(R.id.nav_explore)
        val liveChatItem = navView.menu.findItem(R.id.nav_live_chat)
        val galleryItem = navView.menu.findItem(R.id.nav_gallery)
        val wishListItem = navView.menu.findItem(R.id.nav_wish_list)
        val magazineItem = navView.menu.findItem(R.id.nav_magazine)

        exploreItem.setOnMenuItemClickListener {
            Utilities.toastySuccess(this, getString(R.string.explore))
            mainBinding.drawerLayout.closeDrawer(GravityCompat.START)
            true
        }
        liveChatItem.setOnMenuItemClickListener {
            Utilities.toastySuccess(this, getString(R.string.live_chat))
            mainBinding.drawerLayout.closeDrawer(GravityCompat.START)
            true
        }
        galleryItem.setOnMenuItemClickListener {
            Utilities.toastySuccess(this, getString(R.string.menu_gallery))
            mainBinding.drawerLayout.closeDrawer(GravityCompat.START)
            true
        }
        wishListItem.setOnMenuItemClickListener {
            Utilities.toastySuccess(this, getString(R.string.wish_list))
            mainBinding.drawerLayout.closeDrawer(GravityCompat.START)
            true
        }
        magazineItem.setOnMenuItemClickListener {
            Utilities.toastySuccess(this, getString(R.string.magazine))
            mainBinding.drawerLayout.closeDrawer(GravityCompat.START)
            true
        }

        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }


    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

}
