package loshica.vendor

import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.CheckedTextView
import androidx.appcompat.app.AppCompatActivity
import loshica.vendor.view.LOSAccentDialog.LOSAccentDialogListener
import loshica.vendor.view.LOSRadioBtnDialog.Companion.newInstance
import loshica.vendor.view.LOSRadioBtnDialog.LOSRadioBtnDialogListener
import loshica.vendor.view.LOSAboutDialog
import loshica.vendor.view.LOSAccentDialog
import loshica.vendor.view.LOSRadioBtnDialog
import loshica.vendor.viewModel.LOSTheme

class LOSSettingsActivity : AppCompatActivity(),
    View.OnClickListener, LOSRadioBtnDialogListener, LOSAccentDialogListener {

    private lateinit var accentDialog: LOSAccentDialog
    private lateinit var themeDialog: LOSRadioBtnDialog
    private lateinit var accentView: CheckedTextView
    private lateinit var darkView: CheckedTextView
    private lateinit var aboutView: CheckedTextView
    private var settings: SharedPreferences? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        val theme = LOSTheme(this)
        settings = theme.settings

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        accentView = findViewById(R.id.accent)
        darkView = findViewById(R.id.dark)
        aboutView = findViewById(R.id.about)

        accentView.setOnClickListener(this)
        darkView.setOnClickListener(this)
        aboutView.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.accent -> {
                accentDialog = LOSAccentDialog()
                accentDialog.show(supportFragmentManager, null)
            }
            R.id.dark -> {
                themeDialog = newInstance(
                    resources.getIntArray(R.array.theme_values),
                    resources.getStringArray(R.array.theme_labels),
                    LOSTheme.THEME_KEY, LOSTheme.SETTINGS, LOSTheme.THEME_DEFAULT,
                    R.string.theme_section,
                    settings!!.getInt(LOSTheme.THEME_KEY, LOSTheme.THEME_DEFAULT)
                )
                themeDialog.show(supportFragmentManager, null)
            }
            else -> LOSAboutDialog().show(supportFragmentManager, null)
        }
    }

    override fun change(key: String?, value: Int) {
        val editor = settings!!.edit()
        editor.putInt(key, value)
        editor.apply()
        themeDialog.dismiss()
        recreate()
    }

    override fun setAccent(value: Int) {
        val editor = settings!!.edit()
        editor.putInt(LOSTheme.ACCENT_KEY, value)
        editor.apply()
        accentDialog.dismiss()
        recreate()
    }
}