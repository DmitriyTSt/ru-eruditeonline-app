package ru.eruditeonline.app.presentation.ui.rating.tab

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.eruditeonline.app.data.model.LoadableState
import ru.eruditeonline.app.data.model.rating.RatingRow
import ru.eruditeonline.app.domain.usecase.rating.GetRatingUseCase
import ru.eruditeonline.app.presentation.ui.base.BaseViewModel
import java.time.LocalDate
import javax.inject.Inject

class RatingTabItemViewModel @Inject constructor(
    private val getRatingUseCase: GetRatingUseCase,
) : BaseViewModel() {
    /** Рейтинг */
    private val _ratingLiveData = MutableLiveData<LoadableState<List<RatingRow>>>()
    val ratingLiveData: LiveData<LoadableState<List<RatingRow>>> = _ratingLiveData

    /** Выбранная дата */
    private val _selectedDateLiveData = MutableLiveData<LocalDate>()
    val selectedDateLiveData: LiveData<LocalDate> = _selectedDateLiveData

    fun initLoad(mode: RatingTabItemMode) {
        val date = LocalDate.now().run {
            if (mode == RatingTabItemMode.YEAR) {
                minusYears(1)
            } else {
                this
            }
        }
        load(mode, date)
    }

    fun retry(mode: RatingTabItemMode) {
        val date = selectedDateLiveData.value
        if (date != null) {
            load(mode, date)
        }
    }

    fun selectDate(mode: RatingTabItemMode, date: LocalDate) {
        load(mode, date)
    }

    private fun load(mode: RatingTabItemMode, date: LocalDate) {
        _selectedDateLiveData.postValue(date)
        _ratingLiveData.launchLoadData(
            getRatingUseCase.executeFlow(
                GetRatingUseCase.Params(
                    year = date.year,
                    month = if (mode != RatingTabItemMode.YEAR) date.monthValue else null,
                    day = if (mode == RatingTabItemMode.DAY) date.dayOfMonth else null,
                )
            )
        )
    }
}