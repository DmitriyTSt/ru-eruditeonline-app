package ru.eruditeonline.app.presentation.ui.startactivity

import android.os.Bundle
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import ru.eruditeonline.app.presentation.navigation.MainAppActivity
import ru.eruditeonline.app.presentation.ui.base.BaseActivity

class StartActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)
        splashScreen.setKeepOnScreenCondition { true }
        startActivity(MainAppActivity.createStartIntent(this))
        finish()
    }
}
