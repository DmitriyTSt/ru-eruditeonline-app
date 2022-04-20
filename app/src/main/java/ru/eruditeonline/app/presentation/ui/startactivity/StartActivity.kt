package ru.eruditeonline.app.presentation.ui.startactivity

import android.os.Bundle
import ru.eruditeonline.app.presentation.ui.base.BaseActivity
import ru.eruditeonline.app.presentation.ui.mainactivity.MainActivity

class StartActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startActivity(MainActivity.createStartIntent(this))
        finish()
    }
}
