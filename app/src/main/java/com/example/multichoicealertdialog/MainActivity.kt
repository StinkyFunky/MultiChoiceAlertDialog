package com.example.multichoicealertdialog

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.example.multichoicealertdialog.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var dialog: AlertDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnShow.setOnClickListener {
            showDialog()
        }
    }

    private fun showDialog() {
        val arrayColors = arrayOf("RED", "GREEN", "YELLOW", "BLACK", "MAGENTA", "PINK", "GRAY")
        val arrayChecked = booleanArrayOf(false, false, false, false, false, false, false)
        val builder = AlertDialog.Builder(this)

        builder.setTitle("Choose colors:")

        builder.setMultiChoiceItems(arrayColors, arrayChecked) { _, which, isChecked ->
            arrayChecked[which] = isChecked
            val color = arrayColors[which]
            Snackbar.make(binding.root, "$color clicked.", Snackbar.LENGTH_SHORT).show()
        }

        builder.setPositiveButton("OK") { _, _ ->
            binding.txtView.text = "Your preferred colors:\n\n"
            for (i in arrayColors.indices) {
                val checked = arrayChecked[i]
                if (checked) {
                    binding.txtView.text = "${binding.txtView.text} ${arrayColors[i]}\n\n"
                }
            }
        }

        dialog = builder.create()
        dialog.show()
    }
}
