package loshica.main

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import loshica.vendor.LOSSettingsActivity
import loshica.vendor.LOSTheme

class MainActivity : AppCompatActivity() {

    var appTheme = 0 // Var for check theme

    override fun onCreate(savedInstanceState: Bundle?) {
        // Install theme, when activity created
        appTheme = LOSTheme(this).current
        setTheme(appTheme)
        //

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()
        // If theme changed -> apply new theme
        if (appTheme != LOSTheme(this).current) recreate()
        //
    }

    // Actionbar menu (not necessary, such example to do it)
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.los_menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.los_action_settings) {
            // Method to open settings activity
            startActivity(Intent(this.applicationContext, LOSSettingsActivity::class.java))
            //
        }
        return super.onOptionsItemSelected(item)
    } //
}