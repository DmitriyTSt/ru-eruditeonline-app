package ru.eruditeonline.app.presentation.composeui.mainacitivty

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.MainThread
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelLazy
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.viewmodel.CreationExtras
import dagger.android.AndroidInjection
import ru.eruditeonline.app.domain.usecase.SplashUseCase
import ru.eruditeonline.app.presentation.ui.splash.SplashStartFlowViewModel
import javax.inject.Inject
import kotlin.reflect.KClass

private const val EXTRA_FROM_401_ERROR = "extra_from_401_error"

class MainComposeActivity : ComponentActivity() {

    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: SplashStartFlowViewModel by createViewModelLazy(
        SplashStartFlowViewModel::class,
        { viewModelStore },
        factoryProducer = { viewModelFactory }
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        viewModel.initialFlowLiveEvent.observe(this) { result ->
            result.doOnSuccess { initResult ->
                when (initResult) {
                    is SplashUseCase.Result.AppUpdateScreen -> Unit // TODO
                    SplashUseCase.Result.MainScreen -> Unit // TODO()
                }
            }
        }
        installSplashScreen().setKeepOnScreenCondition {
            viewModel.isReady
        }
        setContent {
            EruditeComposeApp(viewModelFactory = viewModelFactory)
        }
    }

    @MainThread
    private fun <VM : ViewModel> createViewModelLazy(
        viewModelClass: KClass<VM>,
        storeProducer: () -> ViewModelStore,
        extrasProducer: () -> CreationExtras = { defaultViewModelCreationExtras },
        factoryProducer: (() -> ViewModelProvider.Factory)? = null,
    ): Lazy<VM> {
        val factoryPromise = factoryProducer ?: {
            defaultViewModelProviderFactory
        }
        return ViewModelLazy(viewModelClass, storeProducer, factoryPromise, extrasProducer)
    }

    companion object {
        fun createStartIntent(context: Context, from401Error: Boolean = false): Intent {
            return Intent(context, MainComposeActivity::class.java).apply {
                putExtra(EXTRA_FROM_401_ERROR, from401Error)
            }
        }
    }
}

