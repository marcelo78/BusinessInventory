package com.ma.businessinventory.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.annotation.IdRes
import androidx.viewpager.widget.ViewPager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.ma.businessinventory.R

class MainActivity : AppCompatActivity(), Main.View, BottomNavigationView.OnNavigationItemSelectedListener {

    companion object {
        private val TAG = MainActivity::class.java.simpleName
    }

    private lateinit var viewPager: ViewPager
    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var mainPagerAdapter: MainPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(findViewById(R.id.my_toolbar))

        // Initialize components/views.
        viewPager = findViewById(R.id.view_pager);
        bottomNavigationView = findViewById(R.id.navigationView);
        mainPagerAdapter = MainPagerAdapter(supportFragmentManager)

        // Set items to be displayed.
        mainPagerAdapter.setItems(arrayListOf(MainScreen.LOGS, MainScreen.PROGRESS, MainScreen.PROFILE))

        // Show the default screen.
        val defaultScreen = MainScreen.LOGS
        scrollToScreen(defaultScreen)
        selectBottomNavigationViewMenuItem(defaultScreen.menuItemId)
        supportActionBar?.setTitle(defaultScreen.titleStringId)

        // Set the listener for item selection in the bottom navigation view.
        bottomNavigationView.setOnNavigationItemSelectedListener(this)

        // Attach an adapter to the view pager and make it select the bottom navigation
        // menu item and change the title to proper values when selected.
        viewPager.adapter = mainPagerAdapter
        viewPager.addOnPageChangeListener(object : ViewPager.SimpleOnPageChangeListener() {
            override fun onPageSelected(position: Int) {
                val selectedScreen = mainPagerAdapter.getItems()[position]
                selectBottomNavigationViewMenuItem(selectedScreen.menuItemId)
                supportActionBar?.setTitle(selectedScreen.titleStringId)
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.mainmenu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.action_home -> {
            // User chose the "Settings" item, show the app settings UI...
            Log.v(TAG, " ${item.title}")
            true
        }

        R.id.action_search_by_name -> {
            // User chose the "Favorite" action, mark the current item
            // as a favorite...
            Log.v(TAG, " ${item.title}")
            true
        }
        R.id.action_search_by_barcode -> {
            // User chose the "Favorite" action, mark the current item
            // as a favorite...
            Log.v(TAG, " ${item.title}")
            true
        }

        else -> {
            // If we got here, the user's action was not recognized.
            // Invoke the superclass to handle it.
            Log.v(TAG, "any")
            super.onOptionsItemSelected(item)
        }
    }

    /**
     * Scrolls `ViewPager` to show the provided screen.
     */
    private fun scrollToScreen(mainScreen: MainScreen) {
        val screenPosition = mainPagerAdapter.getItems().indexOf(mainScreen)
        if (screenPosition != viewPager.currentItem) {
            viewPager.currentItem = screenPosition
        }
    }

    /**
     * Selects the specified item in the bottom navigation view.
     */
    private fun selectBottomNavigationViewMenuItem(@IdRes menuItemId: Int) {
        bottomNavigationView.setOnNavigationItemSelectedListener(null)
        bottomNavigationView.selectedItemId = menuItemId
        bottomNavigationView.setOnNavigationItemSelectedListener(this)
    }

    /**
     * Listener implementation for registering bottom navigation clicks.
     */
    override fun onNavigationItemSelected(menuItem: MenuItem): Boolean {
        getMainScreenForMenuItem(menuItem.itemId)?.let {
            scrollToScreen(it)
            supportActionBar?.setTitle(it.titleStringId)
            return true
        }
        return false
    }
}
