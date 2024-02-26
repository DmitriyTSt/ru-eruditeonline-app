package ru.eruditeonline.app.presentation.ui.views

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import ru.eruditeonline.app.R
import ru.eruditeonline.app.data.model.base.Score
import ru.eruditeonline.ui.presentation.ext.getColorCompat

class ScoreProgressView : View {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defAttrs: Int) : super(context, attrs, defAttrs)

    private val strokeWidth by lazy { resources.getDimension(R.dimen.score_stroke_width) }
    private val strokeWidthPart by lazy { strokeWidth / 2 }

    private val backgroundPaint by lazy {
        Paint(Paint.ANTI_ALIAS_FLAG).apply {
            color = context.getColorCompat(R.color.light_grey)
            style = Paint.Style.STROKE
            strokeWidth = this@ScoreProgressView.strokeWidth
        }
    }
    private val foregroundPaint by lazy {
        Paint(Paint.ANTI_ALIAS_FLAG).apply {
            color = context.getColorCompat(R.color.green_pea)
            style = Paint.Style.STROKE
            strokeWidth = this@ScoreProgressView.strokeWidth
        }
    }

    private val startAngle = -225f
    private val endAngle = 45f
    private var progressAngle = startAngle

    fun bindScore(score: Score) {
        progressAngle = startAngle + (score.current.toFloat() / score.max * (endAngle - startAngle))
        invalidate()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawArc(
            strokeWidthPart,
            strokeWidthPart,
            width.toFloat() - strokeWidthPart,
            height.toFloat() - strokeWidthPart,
            startAngle,
            endAngle - startAngle,
            false,
            backgroundPaint
        )
        canvas.drawArc(
            strokeWidthPart,
            strokeWidthPart,
            width.toFloat() - strokeWidthPart,
            height.toFloat() - strokeWidthPart,
            startAngle,
            progressAngle - startAngle,
            false,
            foregroundPaint
        )
    }
}
