package com.ma.businessinventory.ui.main

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.SearchView
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.jakewharton.rxbinding2.widget.RxSearchView
import com.ma.businessinventory.R
import com.ma.businessinventory.adapter.MainPagerAdapter
import com.ma.businessinventory.ui.adddetailitem.AddDetailItemActivity
import com.ma.businessinventory.ui.detailitem.ItemDetailActivity
import com.ma.businessinventory.ui.search.Search
import com.ma.businessinventory.ui.search.SearchFragment
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.TimeUnit


class MainActivity : AppCompatActivity(), IMain.View,
    BottomNavigationView.OnNavigationItemSelectedListener {

    companion object {
        private val TAG = MainActivity::class.java.simpleName

        const val ItemId = "PRODUCT_ID"
    }

    lateinit var disposable: Disposable
    lateinit var fm: FragmentManager

    private lateinit var mainPagerAdapter: MainPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(findViewById(R.id.my_toolbar))

        // Initialize components
        mainPagerAdapter = MainPagerAdapter(
            supportFragmentManager,
            FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
        )

        // Set items to be displayed.
        mainPagerAdapter.setItems(
            arrayListOf(
                MainScreen.LOGS,
                MainScreen.PROGRESS,
                MainScreen.PROFILE
            )
        )

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

                my_toolbar.menu.findItem(R.id.action_search_by_name).isVisible = (position == 0)
            }
        })
    }

    override fun onResume() {
        super.onResume()
        fm = supportFragmentManager
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        menu?.findItem(R.id.action_search_by_name)!!.isVisible = (viewPager.currentItem == 0)
        return super.onPrepareOptionsMenu(menu)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.mainmenu, menu)

        val searchMenuItem = menu.findItem(R.id.action_search_by_name)
        setupSearchView(searchMenuItem)

        return true
    }

    @SuppressLint("CheckResult")
    fun setupSearchView(searchMenuItem: MenuItem) {
        val searchView = searchMenuItem.actionView as SearchView
        searchView.queryHint = getString(R.string.search_hint) // your hint here

        RxSearchView.queryTextChanges(searchView)
            .retry(3)
            .debounce(300, TimeUnit.MILLISECONDS)
            .subscribe { newText ->
                Log.d(TAG, "[Rx - SearchView] $newText")
                getSearchFragment(fm)?.let {
                    val view = it as Search.View
                    view.showFilterbyName("$newText")
                }
            }

    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.action_search_by_name -> {
            // User chose the "Favorite" action, mark the current item
            // as a favorite...
            Log.d(TAG, " ${item.title}")
            true
        }
        else -> {
            // If we got here, the user's action was not recognized.
            // Invoke the superclass to handle it.
            Log.d(TAG, "any")
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

    override fun openAddItemActivity(idItem: Int) {
        val intentDetail = ItemDetailActivity.getStartIntent(this).apply {
            putExtra(ItemId, idItem.toLong())
        }
        startActivity(intentDetail)
    }

    override fun openEditItemActivity(idItem: Int) {
        val intentDetail = AddDetailItemActivity.getStartIntent(this).apply {
            putExtra(ItemId, idItem.toLong())
        }
        startActivity(intentDetail)
    }

    fun getSearchFragment(fm: FragmentManager): SearchFragment? {
        val fragments = fm.fragments
        fragments.forEach { fragment: Fragment ->
            if (fragment is SearchFragment) {
                return fragment
            }
        }
        return null
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable.dispose()
    }

}
