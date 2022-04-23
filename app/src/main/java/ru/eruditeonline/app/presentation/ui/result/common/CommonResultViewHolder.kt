package ru.eruditeonline.app.presentation.ui.result.common

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.eruditeonline.app.R
import ru.eruditeonline.app.data.model.test.TestCommonResultRow
import ru.eruditeonline.app.databinding.ItemCommonResultBinding
import ru.eruditeonline.app.presentation.extension.inflate
import ru.eruditeonline.app.presentation.extension.load
import ru.eruditeonline.app.presentation.managers.DateFormatter
import java.time.Instant
import java.time.ZoneId

class CommonResultViewHolder(
    parent: ViewGroup,
    private val dateFormatter: DateFormatter,
    private val onItemClick: (TestCommonResultRow) -> Unit,
) : RecyclerView.ViewHolder(parent.inflate(R.layout.item_common_result)) {
    private val binding by viewBinding(ItemCommonResultBinding::bind)

    fun bind(result: TestCommonResultRow) = with(binding) {
        textViewDate.text = dateFormatter.formatStandardDate(result.date)
        textViewPlace.text = result.resultText
        textViewCity.text = result.city
        textViewUsername.text = result.username
        textViewCompetition.text = result.competitionTitle
        imageViewCountry.load(result.countryIcon)
        root.setOnClickListener {
            onItemClick(result)
        }
    }
}
