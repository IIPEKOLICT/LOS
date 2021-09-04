package loshica.vendor

import android.content.DialogInterface
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import loshica.vendor.databinding.LosActivitySettingsBinding
import loshica.vendor.databinding.LosDialogAboutBinding
import loshica.vendor.interfaces.LOSChangeSettings
import loshica.vendor.view.LOSAccentDialog
import loshica.vendor.view.LOSDialogBuilder
import loshica.vendor.viewModel.LOSTheme

class LOSSettingsActivity : AppCompatActivity(), View.OnClickListener, LOSChangeSettings {

    private lateinit var b: LosActivitySettingsBinding
    private lateinit var settings: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        val theme = LOSTheme(this)
        settings = theme.settings

        super.onCreate(savedInstanceState)
        b = LosActivitySettingsBinding.inflate(layoutInflater)
        setContentView(b.root)

        b.accent.setOnClickListener(this)
        b.dark.setOnClickListener(this)
        b.about.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v) {
            b.accent -> LOSAccentDialog().show(supportFragmentManager, null)
            b.dark -> {
                LOSDialogBuilder(this)
                    .setTitle(resources.getString(R.string.theme_section))
                    .setSingleChoiceItems(
                        resources.getStringArray(R.array.theme_labels),
                        settings.getInt(LOSTheme.THEME_KEY, 0)
                    ) { dialog, which -> changeSettings(dialog, LOSTheme.THEME_KEY, which) }
                    .show()
            }
            else -> {
                LOSDialogBuilder(this)
                    .setTitle(resources.getString(R.string.about_section))
                    .setView(LosDialogAboutBinding.inflate(layoutInflater).root)
                    .show()
            }
        }
    }

    override fun changeSettings(dialog: DialogInterface, key: String, value: Int) {
        val editor = settings.edit()
        editor.putInt(key, value)
        editor.apply()
        dialog.dismiss()
        recreate()
    }
}