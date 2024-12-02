package com.example.doodle

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View


data class PathWithPaint(val path: Path, val paint: Paint)

class DoodleView(context: Context, attrs: AttributeSet?) : View(context, attrs) {
    private val paths = mutableListOf<PathWithPaint>()
    private var currentPath: Path? = null
    private var currentPaint: Paint? = null

    private var currentColor = android.graphics.Color.BLACK
    private var currentBrushSize = 10f

    init {
        createNewPath()
    }

    private fun createNewPath() {
        currentPath = Path()
        currentPaint = Paint().apply {
            color = currentColor
            style = Paint.Style.STROKE
            strokeWidth = currentBrushSize
            isAntiAlias = true
        }
        paths.add(PathWithPaint(currentPath!!, currentPaint!!))
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        for (pathWithPaint in paths) {
            canvas.drawPath(pathWithPaint.path, pathWithPaint.paint)
        }
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                createNewPath()
                currentPath?.moveTo(event.x, event.y)
            }
            MotionEvent.ACTION_MOVE -> {
                currentPath?.lineTo(event.x, event.y)
            }
        }
        invalidate()
        return true
    }


    fun clearCanvas() {
        paths.clear()
        invalidate()
    }


    fun setBrushSize(size: Float) {
        currentBrushSize = size
    }


    fun setBrushColor(color: Int) {
        currentColor = color
    }
}

