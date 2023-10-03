package com.yazid.notes.ui.splash

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.yazid.notes.R
import com.yazid.notes.ui.home.HomeActivity
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        lifecycleScope.launch {
            delay(2000)

            val homeIntent = Intent(this@SplashActivity, HomeActivity::class.java)
            startActivity(homeIntent)
            finish()
        }
    }
}