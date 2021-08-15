package loshica.main;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import loshica.vendor.LOSSettingsActivity; // Settings activity
import loshica.vendor.LOSTheme; // Theme engine

public class MainActivity extends AppCompatActivity {

    int theme; // Var for check theme

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Install theme, when activity created
        theme = new LOSTheme(this).current;
        setTheme(theme);
        //

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        super.onResume();
        // If theme changed -> apply new theme
        if (theme != new LOSTheme(this).current) recreate();
        //
    }

    // Actionbar menu (not necessary, such example to do it)
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.los_menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.los_action_settings) {
            // Method to open settings activity
            startActivity(new Intent(this.getApplicationContext(), LOSSettingsActivity.class));
            //
        }
        return super.onOptionsItemSelected(item);
    }
    //
}