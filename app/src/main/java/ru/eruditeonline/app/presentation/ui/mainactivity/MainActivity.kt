package ru.eruditeonline.app.presentation.ui.mainactivity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.eruditeonline.app.R
import ru.eruditeonline.app.databinding.ActivityMainBinding
import ru.eruditeonline.app.presentation.ui.base.BaseActivity

private const val EXTRA_FROM_401_ERROR = "extra_from_401_error"

class MainActivity : BaseActivity(), BottomNavigationViewManager {

    companion object {
        fun createStartIntent(context: Context, from401Error: Boolean = false): Intent {
            return Intent(context, MainActivity::class.java).apply {
                putExtra(EXTRA_FROM_401_ERROR, from401Error)
            }
        }
    }

    private val binding by viewBinding(ActivityMainBinding::bind)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (intent.getBooleanExtra(EXTRA_FROM_401_ERROR, false)) {
            Toast.makeText(this, R.string.error_auth_401, Toast.LENGTH_SHORT).show()
        }

        val navController = (supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment).navController
        binding.bottomNavigationView.apply {
            setupWithNavController(navController.apply { attachNavController(this) })
        }
    }

    override fun setNavigationViewVisibility(isVisible: Boolean) {
        binding.bottomNavigationView.isVisible = isVisible
    }

    override fun getNavigationView(): View {
        return binding.bottomNavigationView
    }
}
