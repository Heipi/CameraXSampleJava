package com.ai.sample

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.CalendarContract
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.ai.hilt.R
import com.google.android.material.navigation.NavigationView

class TasksActivity : AppCompatActivity() {
    lateinit var drawerLayout:DrawerLayout
    lateinit var navController:NavController
    private lateinit var appBarConfiguration: AppBarConfiguration
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tasks)
        window.statusBarColor = Color.CYAN
        setupNavigationDrawer()
        setSupportActionBar(findViewById(R.id.toolbar))
//        val navController:NavController  =  findNavController(R.id.nav_host_fragment)
        val navHostFragment =
                supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
         navController = navHostFragment.navController
         appBarConfiguration =  AppBarConfiguration(navController.graph,drawerLayout)
         setupActionBarWithNavController(navController,appBarConfiguration)
        findViewById<NavigationView>(R.id.nav_view).setupWithNavController(navController)

    }

    override fun onSupportNavigateUp(): Boolean {
        return  navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()


    }


    fun setupNavigationDrawer(){
            drawerLayout  = findViewById<DrawerLayout>(R.id.drawer_layout)
                    .apply {
                         setStatusBarBackgroundColor(Color.RED)
                    }
    }





}