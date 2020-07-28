package com.cep.padc_x_customview_cep.views.components

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import androidx.core.content.withStyledAttributes
import com.cep.padc_x_customview_cep.R

class CircularProgressBar @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val backgroundPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private lateinit var progressPaint: Paint
    private val labelPaint = Paint(Paint.ANTI_ALIAS_FLAG)

    private var textColor = DEFAULT_TEXT_COLOR
    private var textSize = DEFAULT_TEXT_SIZE
    private var progressColor = DEFAULT_PROGRESS_COLOR
    private var progressWidth = DEFAULT_PROGRESS_WIDTH

    private var progressColorStart = 0
    private var progressColorEnd = 0
    private var progressCurrent = 0
    private var progressMax = 0
    private var progressText = 0


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        val margin = backgroundPaint.strokeWidth/2f

        val rectF = RectF( margin, margin, width-margin, height-margin)

        progressPaint.shader =
            SweepGradient(width/2f, height/2f, progressColorStart, progressColorEnd)

        canvas?.save()
        canvas?.rotate(270f,width/2f, height/2f)
        canvas?.drawArc(rectF, 0F, 360f, false, backgroundPaint)
        canvas?.drawArc(rectF, 0F, (progressCurrent/progressMax) *360f, false, progressPaint)
        canvas?.restore()

        val labelYOffSet = rectF.height()/2f
        canvas?.drawText(
            progressText.toString(), width/2f, height/2f + labelYOffSet, labelPaint
        )
    }
    
    init {
        setUpPaint()
        setUpAttribute(attrs)
    }

    private fun setUpAttribute(attrs: AttributeSet?) {
        context.withStyledAttributes(attrs, R.styleable.CircularProgressBar){
            progressColorStart = getInteger(R.styleable.CircularProgressBar_progressColorStart, -1)
            progressColorEnd = getInteger(R.styleable.CircularProgressBar_progressColorEnd, -1)
            progressCurrent = getInteger(R.styleable.CircularProgressBar_progressCurrent, 0)
            progressMax = getInteger(R.styleable.CircularProgressBar_progressMax, 100)
            textColor = getInteger(R.styleable.CircularProgressBar_progressLabelColor, DEFAULT_TEXT_COLOR)
            textSize = getInteger(R.styleable.CircularProgressBar_progressLabelSize, DEFAULT_TEXT_SIZE)
            progressText = getInteger(R.styleable.CircularProgressBar_progressText, 0)
        }
    }

    private fun setUpPaint(){
        backgroundPaint.isAntiAlias = true
        backgroundPaint.color = Color.GRAY
        backgroundPaint.style = Paint.Style.STROKE

        progressPaint = Paint(backgroundPaint)

        labelPaint.isAntiAlias = true
        labelPaint.textAlign = Paint.Align.CENTER
    }



    companion object{
        const val DEFAULT_TEXT_COLOR = Color.BLACK
        const val DEFAULT_TEXT_SIZE = 14
        const val DEFAULT_PROGRESS_COLOR = Color.CYAN
        const val DEFAULT_PROGRESS_WIDTH = 5.0f
        const val DEFAULT_CIRCLE_RADIUS = 80
    }

}