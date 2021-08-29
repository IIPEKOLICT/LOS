package loshica.main

import android.os.Bundle
import loshica.vendor.LOSActivity

class MainActivity : LOSActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}