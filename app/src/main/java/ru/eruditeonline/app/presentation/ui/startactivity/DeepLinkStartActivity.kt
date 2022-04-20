package ru.eruditeonline.app.presentation.ui.startactivity

import android.os.Bundle
import ru.eruditeonline.app.presentation.managers.DeepLinkManager
import ru.eruditeonline.app.presentation.ui.base.BaseActivity
import ru.eruditeonline.app.presentation.ui.mainactivity.MainActivity
import javax.inject.Inject

class DeepLinkStartActivity : BaseActivity() {

    @Inject lateinit var deepLinkManager: DeepLinkManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        deepLinkManager.setDeepLink(intent.data)
        startActivity(MainActivity.createStartIntent(this))
        finish()
    }
}