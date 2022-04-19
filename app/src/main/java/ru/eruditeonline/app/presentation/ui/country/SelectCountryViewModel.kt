package ru.eruditeonline.app.presentation.ui.country

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.eruditeonline.app.data.model.LoadableState
import ru.eruditeonline.app.data.model.base.Country
import ru.eruditeonline.app.domain.usecase.GetCountriesUseCase
import ru.eruditeonline.app.presentation.ui.base.BaseViewModel
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
