package loshica.vendor.interfaces

import android.content.DialogInterface

interface LOSChangeSettings {
    fun changeSettings(dialog: DialogInterface, key: String, value: Int)
}