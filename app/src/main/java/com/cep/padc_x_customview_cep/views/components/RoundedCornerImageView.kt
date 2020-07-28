package com.cep.padc_x_customview_cep.views.components

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.content.withStyledAttributes
import com.cep.padc_x_customview_cep.R

class RoundedCornerImageView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : AppCompatImageView(context, attrs, defStyleAttr) {

    private var cornerRadius = 0f
    private var strokeWidth = 0f
    private var strokeColor = 0

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)

    private val path = Path()

    init {
        context.withStyledAttributes(attrs, R.styleable.RoundedCornerImageView){
            cornerRadius = getDimension(R.styleable.RoundedCornerImageView_cornerRadius, 0f)
            strokeWidth = getDimension(R.styleable.RoundedCornerImageView_strokeWidth, 0f)
            strokeColor = getColor(R.styleable.RoundedCornerImageView_strokeColor, resources.getColor(android.R.color.white))
        }
    }

    override fun onDraw(canvas: Canvas?) {

        val rectF = RectF(0f, 0f, width.toFloat(), height.toFloat())

        path.addRoundRect(rectF, cornerRadius, cornerRadius, Path.Direction.CCW)


        canvas?.clipPath(path)

        paint.color = strokeColor
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = strokeWidth

        canvas?.drawOval(rectF, paint)

        super.onDraw(canvas)
    }
}