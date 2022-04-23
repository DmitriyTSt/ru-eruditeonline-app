package ru.eruditeonline.app.presentation.ui.views

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import ru.eruditeonline.app.R
import ru.eruditeonline.app.data.model.base.Score
import ru.eruditeonline.app.presentation.extension.getColorCompat

class ScoreProgressView : View {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defAttrs: Int) : super(context, attrs, defAttrs)

    private val backgroundPaint by lazy {
        Paint().apply {
            color = context.getColorCompat(R.color.light_grey)
            strokeWidth = resources.getDimension(R.dimen.score_stroke_width)
        }
    }
    private val foregroundPaint by lazy {
        Paint().apply {
            color = context.getColorCompat(R.color.green_pea)
            strokeWidth = resources.getDimension(R.dimen.score_stroke_width)
        }
    }

    private val startAngle = -225f
    private val endAngle = 45f
    private var progressAngle = startAngle

    fun setScore(score: Score) {
        progressAngle = startAngle + (score.current.toFloat() / score.max * (endAngle - startAngle))
        invalidate()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawArc(
            0f, 0f, width.toFloat(), height.toFloat(),
            startAngle,
            endAngle,
            false,
            backgroundPaint
        )
        canvas.drawArc(
            0f, 0f, width.toFloat(), height.toFloat(),
            startAngle,
            progressAngle,
            false,
            foregroundPaint
        )
    }
}