package ru.eruditeonline.app.presentation.ui.mainactivity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.eruditeonline.app.R
import ru.eruditeonline.app.databinding.ActivityMainBinding
import ru.eruditeonline.app.presentation.ui.base.BaseActivity

class MainActivity : BaseActivity() {

    companion object {
        fun createStartIntent(context: Context): Intent {
            return Intent(context, MainActivity::class.java)
        }
    }

    private val binding by viewBinding(ActivityMainBinding::bind)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navController = (supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment).navController
        binding.bottomNavigationView.setupWithNavController(navController)
    }
}
