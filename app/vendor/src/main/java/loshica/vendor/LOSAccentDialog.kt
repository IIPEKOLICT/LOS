package loshica.vendor

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.CheckedTextView
import android.widget.RadioGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment

class LOSAccentDialog : DialogFragment(), View.OnClickListener {

    private var themeMode = 0
    private var dark = false
    private var settings: SharedPreferences? = null
    private var table: RadioGroup? = null
    private var row1: RadioGroup? = null
    private var row2: RadioGroup? = null
    private var lp: RadioGroup.LayoutParams? = null
    private var listener: LOSAccentDialogListener? = null

    @SuppressLint("ResourceType")
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(requireActivity())
        val inflater = requireActivity().layoutInflater
        val root = inflater.inflate(R.layout.dialog_accent, null)

        settings = requireActivity().getSharedPreferences(LOSTheme.SETTINGS, Context.MODE_PRIVATE)
        themeMode = settings?.getInt(LOSTheme.THEME_KEY, LOSTheme.THEME_DEFAULT)!!
        dark = if (themeMode == 0) LOSTheme.isSystemDark else themeMode < 2
        table = root.findViewById(R.id.accent_root)
        row1 = table?.getChildAt(0)!! as RadioGroup
        row2 = table?.getChildAt(1)!! as RadioGroup
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

            if (settings?.getInt(LOSTheme.ACCENT_KEY, LOSTheme.ACCENT_DEFAULT)!! == i) {
                button.isChecked = true
            }
            if (i - 6 < 0) row1!!.addView(button, lp) else row2!!.addView(button, lp)
        }

        val dialog: Dialog = builder
            .setView(root)
            .setTitle(requireActivity().resources.getText(R.string.accent_section))
            .setPositiveButton(R.string.ok, null)
            .create()

        LOSUtils.dialog(dialog)
        return dialog
    }

    override fun onClick(v: View) {
        listener!!.setAccent(v.id)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = try {
            context as LOSAccentDialogListener
        } catch (e: ClassCastException) {
            throw ClassCastException(context.toString() + e)
        }
    }

    interface LOSAccentDialogListener {
        fun setAccent(value: Int)
    }
}