package ru.eruditeonline.app.presentation.ui.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import dagger.android.AndroidInjection
import ru.eruditeonline.app.presentation.managers.ThemeManager
import javax.inject.Inject

abstract class BaseActivity : AppCompatActivity() {

    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory
    @Inject lateinit var themeManager: ThemeManager

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        setTheme(themeManager.getThemeResource())
        super.onCreate(savedInstanceState)
    }
}
