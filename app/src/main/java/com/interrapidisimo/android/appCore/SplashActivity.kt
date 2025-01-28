package com.interrapidisimo.android.appCore

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.interrapidisimo.android.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        val splashScreen: SplashScreen = installSplashScreen()

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        splashScreen.setKeepOnScreenCondition{false}
        if (Build.VERSION.SDK_INT == Build.VERSION_CODES.M) {
            runBlocking {
                delay(1000)
            }
        }

        startActivity(Intent(this, MainActivity::class.java))
        finish()

    }

}