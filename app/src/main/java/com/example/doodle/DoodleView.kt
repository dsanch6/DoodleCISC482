package com.example.doodle

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

class DoodleView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : View(context, attrs) {

    private val path = Path() // Holds the path drawn by the user
    private val paint = Paint() // Defines how the path is drawn

    companion object {
        private const val DEFAULT_BRUSH_COLOR = android.graphics.Color.BLACK
        private const val DEFAULT_BRUSH_SIZE = 10f
    }

    init {
        // Configure the paint
        paint.color = DEFAULT_BRUSH_COLOR
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = DEFAULT_BRUSH_SIZE
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        // Draw the path on the canvas
        canvas.drawPath(path, paint)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                // Start a new path at the touch point
                path.moveTo(event.x, event.y)
            }
            MotionEvent.ACTION_MOVE -> {
                // Add a line to the path as the user moves their finger
                path.lineTo(event.x, event.y)
            }
        }
        // Request the View to redraw itself
        postInvalidate()
        return true
    }

    // Clear the canvas by resetting the path
    fun clearCanvas() {
        path.reset()
        postInvalidate()
    }

    // Change the brush size
    fun setBrushSize(size: Float) {
        paint.strokeWidth = size
    }

    // Change the brush color
    fun setBrushColor(color: Int) {
        paint.color = color
    }
}
