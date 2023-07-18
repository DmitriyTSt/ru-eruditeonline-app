package ru.eruditeonline.app.presentation.extension

import androidx.annotation.MainThread
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.createViewModelLazy
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelLazy
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.viewmodel.CreationExtras
import ru.eruditeonline.app.presentation.ui.base.BaseActivity
import ru.eruditeonline.app.presentation.ui.base.BaseFragment
import kotlin.reflect.KClass

@MainThread
inline fun <reified VM : ViewModel> BaseFragment.appViewModels() =
    createViewModelLazy(VM::class, { this.viewModelStore }, factoryProducer = { viewModelFactory })

@MainThread
inline fun <reified VM : ViewModel> BaseFragment.appActivityViewModels() =
    createViewModelLazy(
        VM::class,
        { requireActivity().viewModelStore },
        factoryProducer = {
            if (requireActivity() is BaseActivity) {
                (requireActivity() as BaseActivity).viewModelFactory
            } else {
                requireActivity().defaultViewModelProviderFactory
            }
        }
    )

@MainThread
inline fun <reified VM : ViewModel> BaseActivity.appActivityViewModels() =
    createViewModelLazy(
        VM::class,
        { viewModelStore },
        factoryProducer = { viewModelFactory }
    )

@MainThread
fun <VM : ViewModel> AppCompatActivity.createViewModelLazy(
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

