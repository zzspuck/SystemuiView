package com.example.systemui_view

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View

class SlideRevealView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private var bitmapA: Bitmap? = null // 前景图
    private var bitmapB: Bitmap? = null // 背景图
    private var slidingDistance = 0f    // 滑动距离
    private var gradientWidth = 240f    // 渐变宽度
    private val xfermode = PorterDuffXfermode(PorterDuff.Mode.DST_IN)
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)

    // 对外暴露的方法：设置图片
    fun setBitmaps(foreground: Bitmap, background: Bitmap) {
        this.bitmapA = foreground
        this.bitmapB = background
        invalidate()
    }

    // 对外暴露的方法：设置滑动距离 (由 SeekBar 调用)
    fun setSlidingDistance(distance: Float) {
        this.slidingDistance = distance
        invalidate() // 触发重绘
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        // 1. 绘制背景图 B
        bitmapB?.let {
            canvas.drawBitmap(it, null, RectF(0f, 0f, width.toFloat(), height.toFloat()), null)
        }

        // 2. 绘制前景图 A
        bitmapA?.let { foregroundBitmap ->
            // 计算位置
            val leftPosition = kotlin.math.min(slidingDistance, 0f)
            val topPosition = (height - foregroundBitmap.height) / 2f

            // 图层隔离
            val saveCount = canvas.saveLayer(0f, 0f, width.toFloat(), height.toFloat(), null)

            // 绘制 A
            canvas.drawBitmap(foregroundBitmap, leftPosition, topPosition, null)

            // 渐变遮罩
            val gradient = LinearGradient(
                slidingDistance - gradientWidth, 0f,
                slidingDistance, 0f,
                Color.BLACK, Color.TRANSPARENT,
                Shader.TileMode.CLAMP
            )

            paint.shader = gradient
            paint.xfermode = xfermode

            // 绘制遮罩
            canvas.drawRect(
                slidingDistance - gradientWidth, 0f,
                width.toFloat(), height.toFloat(),
                paint
            )

            // 恢复
            canvas.restoreToCount(saveCount)
        }
    }
}