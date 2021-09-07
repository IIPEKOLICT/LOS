package loshica.main

import android.annotation.SuppressLint
import android.os.Bundle
import loshica.main.databinding.ActivityMainBinding
import loshica.vendor.LOSActivity
import loshica.vendor.LOSApp.Companion.isOnline
import loshica.vendor.LOSApp.Companion.json

class MainActivity : LOSActivity() {

    private lateinit var b: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityMainBinding.inflate(layoutInflater)
        setContentView(b.root)
    }

    @SuppressLint("SetTextI18n")
    override fun onResume() {
        super.onResume()
        b.text.text = "online: ${isOnline(application)}"
    }
}