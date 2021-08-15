package loshica.vendor;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

public class LOSAboutDialog extends DialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View root = inflater.inflate(R.layout.dialog_about, null);

        Dialog dialog = builder
            .setView(root)
            .setTitle(requireActivity().getResources().getText(R.string.about_section))
            .setPositiveButton(R.string.ok, null)
            .create();

        LOSUtils.dialog(dialog);
        return dialog;
    }
}
