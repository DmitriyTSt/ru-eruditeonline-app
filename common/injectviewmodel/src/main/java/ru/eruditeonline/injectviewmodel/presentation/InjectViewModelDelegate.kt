package ru.eruditeonline.injectviewmodel.presentation

import androidx.annotation.MainThread
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.createViewModelLazy
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelLazy
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.viewmodel.CreationExtras
import kotlin.reflect.KClass

@MainThread
inline fun <reified VM : ViewModel> Fragment.appViewModels(
    noinline viewModelFactoryProvider: () -> ViewModelProvider.Factory,
) = createViewModelLazy(VM::class, { this.viewModelStore }, factoryProducer = viewModelFactoryProvider)

@MainThread
inline fun <reified VM : ViewModel> Fragment.appActivityViewModels(
    noinline viewModelFactoryProvider: () -> ViewModelProvider.Factory?,
) = createViewModelLazy(
    VM::class,
    { requireActivity().viewModelStore },
    factoryProducer = {
        viewModelFactoryProvider() ?: requireActivity().defaultViewModelProviderFactory
    },
)

@MainThread
inline fun <reified VM : ViewModel> AppCompatActivity.appActivityViewModels(
    noinline viewModelFactoryProvider: () -> ViewModelProvider.Factory,
) = createViewModelLazy(
    VM::class,
    { viewModelStore },
    factoryProducer = viewModelFactoryProvider,
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