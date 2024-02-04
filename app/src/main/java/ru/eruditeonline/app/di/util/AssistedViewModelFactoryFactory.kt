package ru.eruditeonline.app.di.util

import androidx.lifecycle.ViewModel
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton

@Singleton
class AssistedViewModelFactoryFactory @Inject constructor(
    private val creators: Map<
            Class<out ViewModel>,
            @JvmSuppressWildcards Provider<AbstractAssistedViewModelFactory<ViewModel>>
            >,
) {

    fun <T : ViewModel> create(modelClass: Class<T>): AbstractAssistedViewModelFactory<T> {
        val creator = creators[modelClass] ?: creators.entries.firstOrNull {
            modelClass.isAssignableFrom(it.key)
        }?.value ?: throw IllegalArgumentException("unknown model class $modelClass")

        @Suppress("UNCHECKED_CAST")
        return creator.get() as AbstractAssistedViewModelFactory<T>
    }
}