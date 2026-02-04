package ru.eruditeonline.app.presentation.managers

import android.annotation.SuppressLint
import android.app.Activity
import android.graphics.Color
import android.os.Build
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.enableEdgeToEdge
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import androidx.core.view.WindowCompat
import com.google.android.material.internal.EdgeToEdgeUtils

/**
 * Класс для поддержки edge-to-edge
 */
object EdgeToEdgeManager {

    /** Флаг, который говорит о том, что во всём приложении должен использоваться светлый Navigation bar */
    private const val IS_LIGHT_NAVIGATION_BAR = true

    /**
     * Включает edge-to-edge на всех версиях Android.
     *
     * Вызывать в Activity до setContentView()
     * */
    fun enableEdgeToEdge(
        activity: ComponentActivity,
        @ColorRes defaultLightScrim: Int,
        @ColorRes defaultDarkScrim: Int,
    ) {
        val defaultLightScrimColor = ContextCompat.getColor(activity, defaultLightScrim)
        // На Android 7 нельзя сделать иконки навбара темными, поэтому там навбар делаем темным
        // (он сам делается на основе этого параметра)
        val defaultDarkScrimColor = ContextCompat.getColor(activity, defaultDarkScrim)
        activity.enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.auto(
                lightScrim = Color.TRANSPARENT,
                darkScrim = Color.TRANSPARENT,
                detectDarkMode = { true },
            ),
            navigationBarStyle = SystemBarStyle.auto(
                lightScrim = defaultLightScrimColor,
                darkScrim = defaultDarkScrimColor,
                detectDarkMode = { true },
            ),
        )

        WindowCompat.getInsetsController(activity.window, activity.window.decorView).apply {
            // isAppearanceLightStatusBars = true
            // isAppearanceLightNavigationBars = true
        }
    }

    /**
     * Устанавливает флаги FLAG_LAYOUT_NO_LIMITS.
     * Тем самым, навбар на девайсах, где меню тремя кнопками становится прозрачным.
     * @param safeInsets позволяет включить безопасную обработку инсетов.
     *  На API 29 и меньше при использовании NO_LIMITS инсеты не приходят и контент проваливается.
     *  В таком случае NO_LIMITS включаться не будет, если стоит safeInsets = true
     */
    @SuppressLint("RestrictedApi")
    fun enableNoLimits(activity: Activity?, safeInsets: Boolean) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R || !safeInsets) {
            activity?.window?.let { window ->
                window.setFlags(
                    WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                    WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                )
                EdgeToEdgeUtils.setLightNavigationBar(window, !IS_LIGHT_NAVIGATION_BAR) // false
            }
        }
    }

    /**
     * Убирает флаги FLAG_LAYOUT_NO_LIMITS.
     * Тем самым, навбар на девайсах, где меню тремя кнопками становится не прозрачным, а таким, каким должно быть во всей приле
     */
    @SuppressLint("RestrictedApi")
    fun disableNoLimits(activity: Activity?) {
        activity?.window?.let { window ->
            window.clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
            EdgeToEdgeUtils.setLightNavigationBar(window, IS_LIGHT_NAVIGATION_BAR) // true
        }
    }
}
