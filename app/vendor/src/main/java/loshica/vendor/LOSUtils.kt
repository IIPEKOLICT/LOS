package loshica.vendor

import android.animation.ObjectAnimator
import android.app.Dialog
import android.view.Gravity
import android.view.WindowManager

class LOSUtils {

    companion object {

        fun dialog(dialog: Dialog) {
            val window = dialog.window
            val wlp = window!!.attributes

            wlp.gravity = Gravity.BOTTOM
            wlp.horizontalMargin = 0f
            wlp.width = WindowManager.LayoutParams.MATCH_PARENT
            window.attributes = wlp

            dialog.setOnShowListener {
                val view = window.decorView
                ObjectAnimator
                    .ofFloat(view, "translationY", view.height.toFloat(), 0.0f)
                    .start()
            }
        }
    }
}