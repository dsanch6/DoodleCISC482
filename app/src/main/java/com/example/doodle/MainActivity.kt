package com.example.doodle

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.RadioGroup
import android.widget.SeekBar

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val doodleView = findViewById<DoodleView>(R.id.doodleCanvas)

        val clearButton = findViewById<Button>(R.id.clearButton)
        clearButton.setOnClickListener {
            doodleView.clearCanvas()
        }

        val brushSizeSlider = findViewById<SeekBar>(R.id.brushSizeSlider)
        brushSizeSlider.progress = 10
        brushSizeSlider.max = 50
        brushSizeSlider.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                doodleView.setBrushSize(progress.toFloat())
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

        val colorRadioGroup = findViewById<RadioGroup>(R.id.colorRadioGroup)
        colorRadioGroup.setOnCheckedChangeListener { _, checkedId ->
            val color = when (checkedId) {
                R.id.redButton -> Color.RED
                R.id.blackButton -> Color.BLACK
                R.id.blueButton -> Color.BLUE
                R.id.greenButton -> Color.GREEN
                R.id.purpleButton -> Color.MAGENTA
                else -> Color.BLACK
            }
            doodleView.setBrushColor(color)
        }
    }
}
