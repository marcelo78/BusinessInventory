package com.ma.businessinventory.ui.additem

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ma.businessinventory.R

class AddItemActivity : AppCompatActivity() {

    companion object {
        private val TAG = AddItemActivity::class.java.simpleName

        fun getStartIntent(context: Context): Intent {
            return Intent(context, AddItemActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_item)
    }

}
