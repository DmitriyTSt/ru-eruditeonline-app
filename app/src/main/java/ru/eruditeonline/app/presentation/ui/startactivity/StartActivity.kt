package ru.eruditeonline.app.presentation.ui.startactivity

import android.os.Bundle
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import ru.eruditeonline.app.presentation.ui.mainactivity.MainActivity
import ru.eruditeonline.ui.presentation.base.BaseActivity

class StartActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)
        splashScreen.setKeepOnScreenCondition { true }
        startActivity(MainActivity.createStartIntent(this))
        finish()
    }
}
