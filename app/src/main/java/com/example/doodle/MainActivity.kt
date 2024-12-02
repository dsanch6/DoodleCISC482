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
        setContentView(R.layout.activity_main) // Make sure this matches your XML file

        // Reference the DoodleView
        val doodleView = findViewById<DoodleView>(R.id.doodleCanvas)

        // Handle the Clear button
        val clearButton = findViewById<Button>(R.id.clearButton)
        clearButton.setOnClickListener {
            doodleView.clearCanvas()
        }

        // Handle the Brush Size slider
        val brushSizeSlider = findViewById<SeekBar>(R.id.brushSizeSlider)
        brushSizeSlider.progress = 10 // Default brush size
        brushSizeSlider.max = 50 // Maximum brush size
        brushSizeSlider.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                doodleView.setBrushSize(progress.toFloat())
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                // Do nothing for now
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                // Do nothing for now
            }
        })

        // Handle the Color Radio Buttons
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
