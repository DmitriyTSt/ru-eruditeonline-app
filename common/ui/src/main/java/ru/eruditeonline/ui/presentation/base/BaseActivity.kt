package ru.eruditeonline.ui.presentation.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dagger.android.AndroidInjection
import ru.eruditeonline.injectviewmodel.di.util.InjectViewModelFactory
import ru.eruditeonline.ui.presentation.theme.ThemeManager
import javax.inject.Inject

abstract class BaseActivity : AppCompatActivity() {

    @Inject lateinit var viewModelFactory: InjectViewModelFactory
    @Inject lateinit var themeManager: ThemeManager

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        setTheme(themeManager.getThemeResource())
        super.onCreate(savedInstanceState)
    }
}
