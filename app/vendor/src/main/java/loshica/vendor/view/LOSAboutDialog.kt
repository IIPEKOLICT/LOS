package loshica.vendor.view

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import loshica.vendor.LOSUtils
import loshica.vendor.R

class LOSAboutDialog : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(requireActivity())
        val inflater = requireActivity().layoutInflater
        val root = inflater.inflate(R.layout.dialog_about, null)
        val dialog: Dialog = builder
            .setView(root)
            .setTitle(requireActivity().resources.getText(R.string.about_section))
            .setPositiveButton(R.string.ok, null)
            .create()

        LOSUtils.dialog(dialog)
        return dialog
    }
}