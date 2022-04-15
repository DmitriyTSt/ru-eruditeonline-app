package ru.eruditeonline.app.presentation.ui.result.user

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.eruditeonline.app.R
import ru.eruditeonline.app.data.model.test.TestUserResultRow
import ru.eruditeonline.app.databinding.ItemUserResultBinding
import ru.eruditeonline.app.presentation.extension.inflate
import ru.eruditeonline.app.presentation.managers.DateFormatter
import java.time.Instant
import java.time.ZoneId

class UserResultViewHolder(
    parent: ViewGroup,
    private val dateFormatter: DateFormatter,
    private val onItemClick: (TestUserResultRow) -> Unit,
) : RecyclerView.ViewHolder(parent.inflate(R.layout.item_user_result)) {
    private val binding by viewBinding(ItemUserResultBinding::bind)

    @SuppressLint("SetTextI18n")
    fun bind(result: TestUserResultRow) = with(binding) {
        textViewDate.text = dateFormatter.formatStandardDate(
            Instant.ofEpochSecond(result.date).atZone(ZoneId.systemDefault()).toLocalDate()
        )
        textViewNumber.text = "${result.testId} № ${result.id}"
        textViewUsername.text = result.username
        textViewPlaceValue.text = result.place
        textViewScoreValue.text = root.context.getString(R.string.score_template, result.score.current, result.score.max)
        textViewCompetition.text = result.competitionTitle
        root.setOnClickListener {
            onItemClick(result)
        }
    }
}
