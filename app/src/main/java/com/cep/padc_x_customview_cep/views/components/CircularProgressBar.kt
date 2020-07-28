package com.cep.padc_x_customview_cep.views.components

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import android.view.animation.DecelerateInterpolator
import androidx.core.content.withStyledAttributes
import com.cep.padc_x_customview_cep.R

class CircularProgressBar @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)

    private var viewWidth = 0
    private var viewHeight = 0
    private var mStartAngle = -90F // always start at top
    private var mSweepAngle = 0f // how long to sweep from start angle
    private var mMaxSweepAngle = 360f // full circle
    private var mMaxProgress = 100
    private var mAnimationDuration = 400
    private var progressColor = DEFAULT_PROGRESS_COLOR
    private var mStrokeWidth = 20
    private var textColor = DEFAULT_TEXT_COLOR
    private var mDrawText = true
    private var roundedCorner = true

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        initMeasure()
        drawOutlineArc(canvas)
        if (mDrawText)
            drawText(canvas)
    }

    private fun drawText(canvas: Canvas?) {
        paint.textSize = viewWidth.coerceAtMost(viewHeight) / 5f
        paint.textAlign = Paint.Align.CENTER
        paint.strokeWidth = 0f
        paint.color = textColor

        val xPos = canvas?.width?.div(2)
        val yPos = (canvas?.height?.div(2)?.minus((paint.descent() + paint.ascent()) /2))?.toInt()

        canvas?.drawText(
            "${calculateProgressFromSweepAngle(mSweepAngle)}%",
            xPos!!.toFloat(), yPos!!.toFloat(), paint
        )

    }

    private fun calculateProgressFromSweepAngle(sweepAngle: Float): Int {
        return (sweepAngle * mMaxProgress / mMaxSweepAngle).toInt()
    }

    private fun drawOutlineArc(canvas: Canvas?) {
        val diameter = viewWidth.coerceAtMost(viewHeight)
        val pad = mStrokeWidth / 2.0f
        val outerOval = RectF(pad, pad, diameter - pad, diameter - pad)

        paint.color = progressColor
        paint.strokeWidth = mStrokeWidth.toFloat()
        paint.strokeCap = if (roundedCorner) Paint.Cap.ROUND else Paint.Cap.BUTT
        paint.style = Paint.Style.STROKE

        canvas?.drawArc(outerOval, mStartAngle, mSweepAngle, false, paint)
    }

    private fun initMeasure() {
        viewHeight = height
        viewWidth = width
    }

    init {
        paint.isAntiAlias = true
        setUpAttribute(attrs)
    }

    private fun setUpAttribute(attrs: AttributeSet?) {
        context.withStyledAttributes(attrs, R.styleable.CircularProgressBar) {
            progressColor =
                getColor(R.styleable.CircularProgressBar_progressColor, DEFAULT_PROGRESS_COLOR)
            mStrokeWidth = getInteger(R.styleable.RoundedCornerImageView_strokeWidth, 20)
            textColor =
                getColor(R.styleable.CircularProgressBar_android_textColor, DEFAULT_TEXT_COLOR)
            mDrawText = getBoolean(R.styleable.CircularProgressBar_showProgressText, true)
            roundedCorner = getBoolean(R.styleable.CircularProgressBar_useRoundedCorner, true)
        }
    }

    fun setProgress(progress: Int) {
        val animator =
            ValueAnimator.ofFloat(mSweepAngle, calculateSweepAngleFromProgress(progress))
        animator.interpolator = DecelerateInterpolator()
        animator.duration = mAnimationDuration.toLong()
        animator.addUpdateListener { valueAnimator ->
            mSweepAngle = valueAnimator.animatedValue as Float
            invalidate()
        }
        animator.start()
    }

    private fun calculateSweepAngleFromProgress(progress: Int): Float {
        return mMaxSweepAngle / mMaxProgress * progress
    }


    companion object {
        const val DEFAULT_TEXT_COLOR = Color.BLACK
        const val DEFAULT_PROGRESS_COLOR = Color.CYAN
    }

}