package loshica.vendor;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckedTextView;

import androidx.appcompat.app.AppCompatActivity;

public class LOSSettingsActivity extends AppCompatActivity implements
    View.OnClickListener,
    LOSRadioButtonDialog.RadioButtonDialogListener,
    LOSAccentDialog.LOSAccentDialogListener {

    LOSAccentDialog accentDialog;
    LOSRadioButtonDialog themeDialog;

    CheckedTextView accentView, darkView, aboutView;
    SharedPreferences settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        LOSTheme theme = new LOSTheme(this);
        settings = theme.settings;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        accentView = findViewById(R.id.accent);
        darkView = findViewById(R.id.dark);
        aboutView = findViewById(R.id.about);

        accentView.setOnClickListener(this);
        darkView.setOnClickListener(this);
        aboutView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.accent) {
            accentDialog = new LOSAccentDialog();
            accentDialog.show(getSupportFragmentManager(), null);
        } else if (v.getId() == R.id.dark) {
            themeDialog = LOSRadioButtonDialog.newInstance(
                getResources().getIntArray(R.array.theme_values),
                getResources().getStringArray(R.array.theme_labels),
                LOSTheme.THEME_KEY, LOSTheme.SETTINGS, LOSTheme.THEME_DEFAULT,
                R.string.theme_section,
                settings.getInt(LOSTheme.THEME_KEY, LOSTheme.THEME_DEFAULT)
            );
            themeDialog.show(getSupportFragmentManager(), null);
        } else {
            new LOSAboutDialog().show(getSupportFragmentManager(), null);
        }
    }

    @Override
    public void change(String key, int value) {
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt(key, value);
        editor.apply();
        themeDialog.dismiss();
        recreate();
    }

    @Override
    public void setAccent(int value) {
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt(LOSTheme.ACCENT_KEY, value);
        editor.apply();
        accentDialog.dismiss();
        recreate();
    }
}
