package com.example.bitmapanimationapp

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import androidx.core.content.ContextCompat


class CustomAnimationView : View {
    private var bitmap: Bitmap? = null
    private var bitmapX = 0f
    private var bitmapY = 0f

    private var bitmapWidth = 2 * 19.818f.dp
    private var bitmapHeight = 2 * 13.7115f.dp

    val drawablePaint = Paint(Paint.ANTI_ALIAS_FLAG)

    constructor(context: Context?) : super(context) {
        init()
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        init()
    }

    private fun init() {
        // Initialize your bitmap and set its initial position
//        bitmap = BitmapFactory.decodeResource(resources, R.drawable.ic_premium_user_icon)
        bitmap = ContextCompat.getDrawable(context, R.drawable.ic_premium_user_icon)?.toBitmap(bitmapWidth.toInt(), bitmapHeight.toInt())
        bitmapX = 200f.dp
        bitmapY = 200f.dp

        // Start the animation
        startAnimation()
    }

    private fun startAnimation() {
        val animator = ValueAnimator.ofFloat(0f, 1f)
        animator.duration = 3000 // Duration of the animation in milliseconds
        animator.addUpdateListener { valueAnimator -> // Update the properties of the bitmap based on the animation progress
            val progress = valueAnimator.animatedValue as Float
            alphaAnimation(progress)
            scaleAnimation(progress)
            // Invalidate the view to trigger a redraw
            invalidate()
        }
        animator.start()
    }

    private fun alphaAnimation(progress: Float) {
        drawablePaint.alpha = (255 * progress).toInt()
    }

    private fun translateAnimation(progress: Float) {
        bitmapX = progress * width // Example: Move the bitmap horizontally

    }

    private fun scaleAnimation(progress: Float) {
        val width = (Math.abs(2*bitmapWidth * progress) + bitmapWidth).toInt()
        val height = (Math.abs(2*bitmapHeight * progress) + bitmapHeight).toInt()

        bitmap = ContextCompat.getDrawable(context, R.drawable.ic_premium_user_icon)?.toBitmap(width, height)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val _bitmap = bitmap ?: return
        // Draw the bitmap at its current position
        canvas.drawBitmap(_bitmap, bitmapX - _bitmap.width / 2, bitmapY - _bitmap.height/2, drawablePaint)
    }
}