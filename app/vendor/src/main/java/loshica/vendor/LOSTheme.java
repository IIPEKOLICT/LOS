package loshica.vendor;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class LOSTheme {

    public static final String SETTINGS = "settings";
    public static final String ACCENT_KEY = "accent";
    public static final String THEME_KEY = "theme";
    public static final int ACCENT_DEFAULT = 2;
    public static final int THEME_DEFAULT = 0;

    public static final int[] coloredBgs = new int[]{
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
    };

    private final int[] darkThemes = new int[]{
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
    };

    private final int[] lightThemes = new int[]{
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
    };

    public SharedPreferences settings;
    public int current;
    public static boolean isSystemDark;

    public LOSTheme(Activity activity) {
        this.settings = activity.getSharedPreferences(SETTINGS, Context.MODE_PRIVATE);

        int theme = settings.getInt(THEME_KEY, THEME_DEFAULT);
        int accent = settings.getInt(ACCENT_KEY, ACCENT_DEFAULT);

        this.current = set(theme, accent, activity);
        activity.setTheme(current);
    }

    private int set(int theme, int accent, Activity activity) {
        isSystemDark = activity.getResources().getConfiguration().uiMode > 24;
        switch (theme) {
            case 0: return (isSystemDark) ? darkThemes[accent] : lightThemes[accent];
            case 1: return darkThemes[accent];
            default: return lightThemes[accent];
        }
    }
}

