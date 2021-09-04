package loshica.vendor.view

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.CheckedTextView
import android.widget.RadioGroup
import androidx.fragment.app.DialogFragment
import loshica.vendor.R
import loshica.vendor.databinding.LosDialogAccentBinding
import loshica.vendor.interfaces.LOSDialogChangeSettings
import loshica.vendor.viewModel.LOSTheme

class LOSAccentDialog : DialogFragment(), View.OnClickListener, LOSDialogChangeSettings {

    private var _b: LosDialogAccentBinding? = null
    private val b get() = _b!!

    private var themeMode = 0
    private var dark = false
    private lateinit var settings: SharedPreferences
    private lateinit var lp: RadioGroup.LayoutParams

    @SuppressLint("ResourceType")
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = LOSDialogBuilder(requireActivity())
        _b = LosDialogAccentBinding.inflate(requireActivity().layoutInflater)

        settings = requireActivity().getSharedPreferences(LOSTheme.SETTINGS, Context.MODE_PRIVATE)
        themeMode = settings.getInt(LOSTheme.THEME_KEY, LOSTheme.THEME_DEFAULT)
        dark = if (themeMode == 0) LOSTheme.isSystemDark else themeMode < 2
        lp = RadioGroup.LayoutParams(120, 120)

        for (i in 0 until LOSTheme.coloredBgs.size - 1) {
            val button = CheckedTextView(this.context)

            button.setText(R.string.colored_btn_text)
            button.id = i
            button.gravity = Gravity.CENTER
            button.textAlignment = View.TEXT_ALIGNMENT_CENTER
            button.setTextAppearance(R.style.LOSColoredBthStyle)
            button.setBackgroundResource(
                if (i == LOSTheme.coloredBgs.size - 2 && dark) LOSTheme.coloredBgs[i + 1] else LOSTheme.coloredBgs[i]
            )
            button.setOnClickListener(this)

            if (settings.getInt(LOSTheme.ACCENT_KEY, LOSTheme.ACCENT_DEFAULT) == i) {
                button.isChecked = true
            }
            if (i - 6 < 0) b.row0.addView(button, lp) else b.row1.addView(button, lp)
        }

        return builder
            .setView(b.root)
            .setTitle(requireActivity().resources.getText(R.string.accent_section))
            .create()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _b = null
    }

    override fun onClick(v: View) {
        changeSettings(LOSTheme.ACCENT_KEY, v.id)
    }

    override fun changeSettings(key: String, value: Int) {
        val editor = settings.edit()
        editor.putInt(key, value)
        editor.apply()
        this.dismiss()
        requireActivity().recreate()
    }
}