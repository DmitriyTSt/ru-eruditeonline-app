package ru.eruditeonline.app.presentation.ui.base

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.core.view.doOnPreDraw
import androidx.core.view.marginBottom
import androidx.core.view.marginTop
import androidx.core.view.updatePadding
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import dagger.android.support.AndroidSupportInjection
import ru.eruditeonline.app.di.util.AssistedViewModelFactoryFactory
import ru.eruditeonline.app.presentation.ui.mainactivity.BottomNavigationViewManager
import javax.inject.Inject

abstract class BaseFragment(@LayoutRes layoutRes: Int) : Fragment(layoutRes) {

    open val showBottomNavigationView: Boolean
        get() = (parentFragment as? BaseFragment)?.showBottomNavigationView ?: false

    protected var bottomNavigationViewManager: BottomNavigationViewManager? = null

    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory
    @Inject lateinit var assistedViewModelFactoryFactory: AssistedViewModelFactoryFactory

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        if (context is BottomNavigationViewManager) {
            bottomNavigationViewManager = context
        }
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycleScope.launchWhenStarted {
            callOperations()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupLayout(savedInstanceState)
        onBindViewModel()
        applyBottomNavigationPadding(view)
        bottomNavigationViewManager?.setNavigationViewVisibility(showBottomNavigationView)
    }

    open fun callOperations() = Unit
    open fun setupLayout(savedInstanceState: Bundle?) = Unit
    open fun onBindViewModel() = Unit

    private fun applyBottomNavigationPadding(view: View) {
        if (showBottomNavigationView) {
            bottomNavigationViewManager?.getNavigationView()?.apply {
                if (height > 0) {
                    applyBottomNavigationViewPadding(
                        view,
                        height + marginTop + marginBottom
                    )
                } else {
                    doOnPreDraw {
                        applyBottomNavigationViewPadding(
                            view,
                            height + marginTop + marginBottom
                        )
                    }
                }
            }
        }
    }

    /**
     * Метод выставляет обработку отступа от нижнего меню, если оно есть
     * Необходимо переопределить во фрагменте для кастомного поведения
     */
    protected open fun applyBottomNavigationViewPadding(view: View, bottomNavigationViewHeight: Int) {
        view.updatePadding(bottom = view.paddingBottom + bottomNavigationViewHeight)
    }

    protected infix fun <T> LiveData<T>.observe(block: (T) -> Unit) {
        observe(this@BaseFragment.viewLifecycleOwner) { t -> block.invoke(t) }
    }
}
