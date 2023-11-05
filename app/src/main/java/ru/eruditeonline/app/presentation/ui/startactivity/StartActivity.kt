package ru.eruditeonline.app.presentation.ui.startactivity

import android.os.Bundle
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import ru.eruditeonline.app.presentation.navigation.AppStarter
import ru.eruditeonline.app.presentation.ui.base.BaseActivity
import javax.inject.Inject

class StartActivity : BaseActivity() {

    @Inject lateinit var appStarter: AppStarter

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)
        splashScreen.setKeepOnScreenCondition { true }
        startActivity(appStarter.createStartIntent(this))
        finish()
    }
}
