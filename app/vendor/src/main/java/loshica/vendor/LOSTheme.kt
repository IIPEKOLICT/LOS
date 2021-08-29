package loshica.vendor

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences

class LOSTheme(activity: Activity) {

    private val darkThemes = intArrayOf(
        R.style.LOSTheme_BlackOxygen,
        R.style.LOSTheme_BlackViolet,
        R.style.LOSTheme_BlackRed,
        R.style.LOSTheme_BlackBrown,
        R.style.LOSTheme_BlackCyan,
        R.style.LOSTheme_BlackDBlue,
        R.style.LOSTheme_BlackOrange,
        R.style.LOSTheme_BlackPink,
        R.style.LOSTheme_BlackDGreen,
        R.style.LOSTheme_BlackLGreen,
        R.style.LOSTheme_BlackAosp,
        R.style.LOSTheme_BlackWhite
    )
    private val lightThemes = intArrayOf(
        R.style.LOSTheme_LightOxygen,
        R.style.LOSTheme_LightViolet,
        R.style.LOSTheme_LightRed,
        R.style.LOSTheme_LightBrown,
        R.style.LOSTheme_LightCyan,
        R.style.LOSTheme_LightDBlue,
        R.style.LOSTheme_LightOrange,
        R.style.LOSTheme_LightPink,
        R.style.LOSTheme_LightDGreen,
        R.style.LOSTheme_LightLGreen,
        R.style.LOSTheme_LightAosp,
        R.style.LOSTheme_LightBlack
    )

    var settings: SharedPreferences = activity.getSharedPreferences(SETTINGS, Context.MODE_PRIVATE)
    var current: Int

    private operator fun set(theme: Int, accent: Int, activity: Activity): Int {
        isSystemDark = activity.resources.configuration.uiMode > 24
        return when (theme) {
            0 -> if (isSystemDark) darkThemes[accent] else lightThemes[accent]
            1 -> darkThemes[accent]
            else -> lightThemes[accent]
        }
    }

    companion object {
        const val SETTINGS = "settings"
        const val ACCENT_KEY = "accent"
        const val THEME_KEY = "theme"
        const val ACCENT_DEFAULT = 2
        const val THEME_DEFAULT = 0

        val coloredBgs = intArrayOf(
            R.drawable.colored_btn_bg_oxygen,
            R.drawable.colored_btn_bg_violet,
            R.drawable.colored_btn_bg_red,
            R.drawable.colored_btn_bg_brown,
            R.drawable.colored_btn_bg_cyan,
            R.drawable.colored_btn_bg_dblue,
            R.drawable.colored_btn_bg_orange,
            R.drawable.colored_btn_bg_pink,
            R.drawable.colored_btn_bg_dgreen,
            R.drawable.colored_btn_bg_lgreen,
            R.drawable.colored_btn_bg_aosp,
            R.drawable.colored_btn_bg_black,
            R.drawable.colored_btn_bg_white
        )
        var isSystemDark = false
    }

    init {
        val theme = settings.getInt(THEME_KEY, THEME_DEFAULT)
        val accent = settings.getInt(ACCENT_KEY, ACCENT_DEFAULT)
        current = set(theme, accent, activity)
        activity.setTheme(current)
    }
}