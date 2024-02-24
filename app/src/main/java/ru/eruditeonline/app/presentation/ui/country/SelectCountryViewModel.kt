package ru.eruditeonline.app.presentation.ui.country

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.eruditeonline.app.data.model.base.Country
import ru.eruditeonline.app.domain.usecase.GetCountriesUseCase
import ru.eruditeonline.app.domain.usecase.base.executeFlow
import ru.eruditeonline.architecture.presentation.base.BaseViewModel
import ru.eruditeonline.architecture.presentation.model.LoadableState
import javax.inject.Inject

class SelectCountryViewModel @Inject constructor(
    private val getCountriesUseCase: GetCountriesUseCase,
) : BaseViewModel() {
    /** Страны */
    private val _countriesLiveData = MutableLiveData<LoadableState<List<Country>>>()
    val countriesLiveData: LiveData<LoadableState<List<Country>>> = _countriesLiveData

    fun loadCountries() {
        _countriesLiveData.launchLoadData(getCountriesUseCase.executeFlow(Unit))
    }
}
