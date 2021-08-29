package loshica.vendor.view

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.Gravity
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import loshica.vendor.LOSUtils
import loshica.vendor.R

class LOSRadioBtnDialog : DialogFragment() {

    private var values: IntArray? = null
    private var labels: Array<String>? = null
    private var key: String? = null
    private var settings: String? = null
    private var def = 0
    private var title = 0
    private var checked = 0
    private var rg: RadioGroup? = null
    private var listener: LOSRadioBtnDialogListener? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(requireActivity())
        val inflater = requireActivity().layoutInflater
        val root = inflater.inflate(R.layout.dialog_radio_button, null)

        assert(arguments != null)
        values = requireArguments().getIntArray(ARG_VALUES)
        labels = requireArguments().getStringArray(ARG_LABELS)
        key = requireArguments().getString(ARG_KEY)
        settings = requireArguments().getString(ARG_SETTINGS)
        def = requireArguments().getInt(ARG_DEFAULT)
        title = requireArguments().getInt(ARG_TITLE)
        checked = requireArguments().getInt(ARG_CHECKED)

        rg = root.findViewById(R.id.rbd_rg)
        for (i in values!!.indices) {
            val rb = RadioButton(this.context)
            rb.text = labels!![i]
            rb.id = i
            rb.gravity = Gravity.CENTER_VERTICAL
            rg?.addView(rb, RadioGroup.LayoutParams(RadioGroup.LayoutParams.MATCH_PARENT, 120))
        }
        rg?.check(checked)
        rg?.setOnCheckedChangeListener { _: RadioGroup?, checkedId: Int -> listener!!.change(key, checkedId) }

        val dialog: Dialog = builder
            .setView(root)
            .setTitle(requireActivity().resources.getText(title))
            .setPositiveButton(R.string.ok, null)
            .create()

        LOSUtils.dialog(dialog)
        return dialog
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = try {
            context as LOSRadioBtnDialogListener
        } catch (e: ClassCastException) {
            throw ClassCastException(context.toString() + e)
        }
    }

    interface LOSRadioBtnDialogListener {
        fun change(key: String?, value: Int)
    }

    companion object {

        private const val ARG_VALUES = "values"
        private const val ARG_LABELS = "labels"
        private const val ARG_KEY = "key"
        private const val ARG_SETTINGS = "settings"
        private const val ARG_DEFAULT = "default"
        private const val ARG_TITLE = "title"
        private const val ARG_CHECKED = "checked"

        fun newInstance(
            values: IntArray?, labels: Array<String?>?, key: String?, settings: String?, def: Int,
            title: Int, checked: Int
        ): LOSRadioBtnDialog {
            val dialog = LOSRadioBtnDialog()
            val args = Bundle()

            args.putIntArray(ARG_VALUES, values)
            args.putStringArray(ARG_LABELS, labels)
            args.putString(ARG_KEY, key)
            args.putString(ARG_SETTINGS, settings)
            args.putInt(ARG_DEFAULT, def)
            args.putInt(ARG_TITLE, title)
            args.putInt(ARG_CHECKED, checked)

            dialog.arguments = args
            return dialog
        }
    }
}