package com.example.bitmapanimationapp

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable


var Int.dp: Int
    get() {
        return (this * Resources.getSystem().displayMetrics.density).toInt()
    }
    set(value) {}

var Float.dp: Float
    get() {
        return this * Resources.getSystem().displayMetrics.density
    }
    set(value) {}

var Int.sp: Int
    get() {
        return (this * Resources.getSystem().displayMetrics.scaledDensity).toInt()
    }
    set(value) {}

var Float.sp: Float
    get() {
        return (this * Resources.getSystem().displayMetrics.scaledDensity)
    }
    set(value) {}


fun Drawable.toBitmap(): Bitmap {
    if (this is BitmapDrawable) {
        return bitmap
    }

    val width = if (bounds.isEmpty) intrinsicWidth else bounds.width()
    val height = if (bounds.isEmpty) intrinsicHeight else bounds.height()

    return Bitmap.createBitmap(width.nonZero(), height.nonZero(), Bitmap.Config.ARGB_8888).also {
        val canvas = Canvas(it)
        setBounds(0, 0, canvas.width, canvas.height)
        draw(canvas)
    }
}

fun Drawable.toBitmap(width:Int, height:Int): Bitmap {
    if (this is BitmapDrawable) {
        return bitmap
    }

    return Bitmap.createBitmap(width.nonZero(), height.nonZero(), Bitmap.Config.ARGB_8888).also {
        val canvas = Canvas(it)
        setBounds(0, 0, width, height)
        draw(canvas)
    }
}

private fun Int.nonZero() = if (this <= 0) 1 else this
