package com.example.doodle

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

class DoodleView(context: Context, attrs: AttributeSet?) : View(context, attrs) {
    private val paths = mutableListOf<Pair<Path, Paint>>()
    private var currentPath = Path()
    private var currentPaint = Paint().apply {
        color = android.graphics.Color.BLACK
        style = Paint.Style.STROKE
        strokeWidth = 10f
    }

    init {
        setLayerType(LAYER_TYPE_SOFTWARE, null)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        for ((path, paint) in paths) {
            canvas.drawPath(path, paint)
        }
        canvas.drawPath(currentPath, currentPaint)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> currentPath.moveTo(event.x, event.y)
            MotionEvent.ACTION_MOVE -> currentPath.lineTo(event.x, event.y)
            MotionEvent.ACTION_UP -> {
                paths.add(Pair(Path(currentPath), Paint(currentPaint)))
                currentPath.reset()
            }
        }
        invalidate()
        return true
    }

    fun setBrushSize(size: Float) {
        currentPaint.strokeWidth = size
    }

    fun setBrushColor(color: Int) {
        currentPaint.color = color
    }

    fun clearCanvas() {
        paths.clear()
        currentPath.reset()
        invalidate()
    }

    fun undoLastPath() {
        if (paths.isNotEmpty()) {
            paths.removeLast()
            invalidate()
        }
    }
}
