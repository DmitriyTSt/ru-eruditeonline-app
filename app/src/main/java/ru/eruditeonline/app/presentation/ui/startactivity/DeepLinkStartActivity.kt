package ru.eruditeonline.app.presentation.ui.startactivity

import android.os.Bundle
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import ru.eruditeonline.app.presentation.managers.DeepLinkManager
import ru.eruditeonline.app.presentation.ui.base.BaseActivity
import ru.eruditeonline.app.presentation.ui.mainactivity.MainActivity
import javax.inject.Inject

class DeepLinkStartActivity : BaseActivity() {

    @Inject lateinit var deepLinkManager: DeepLinkManager

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)
        splashScreen.setKeepOnScreenCondition { true }
        deepLinkManager.setDeepLink(intent.data)
        startActivity(MainActivity.createStartIntent(this))
        finish()
    }
}
