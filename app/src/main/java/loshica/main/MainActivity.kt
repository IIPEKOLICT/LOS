package loshica.main

import android.os.Bundle
import loshica.main.databinding.ActivityMainBinding
import loshica.vendor.LOSActivity

class MainActivity : LOSActivity() {

    private lateinit var b: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityMainBinding.inflate(layoutInflater)
        setContentView(b.root)
    }
}