package com.example.test

import android.os.Bundle
import loshica.vendor.LOSActivity

class MainActivity : LOSActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

//    @Composable
//    fun layout() {
//        Text(text = "zalupa")
//    }

//    @Composable
//    fun sectionHeader(text: String) {
//        Text(
//            modifier = Modifier
//        )
//    }
}