package com.example.lecturetask

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*
import java.io.FileOutputStream
import com.example.lecturetask.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {

    private lateinit var binding: ActivityResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityResultBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val score: String? = intent.getStringExtra("counter")
        binding.resultText.text = score
        Log.d("HHH", "$score")

        binding.tryAgainBtn.setOnClickListener {
            startActivity(Intent(this, Menu::class.java))
        }
        binding.exitButton.setOnClickListener {
            val timestamp =
                SimpleDateFormat("yyyy.MM.dd  HH:mm:ss", Locale.getDefault()).format(Date())
            val filename = "test_results.txt"
            val fileContents = "\n-------------------------------------------\nTime:  $timestamp,\n$score"
            val fileOutput = FileOutputStream(filesDir.toString() + "/" + filename, true)
            fileOutput.write(fileContents.toByteArray())
            fileOutput.close()
            val toast = Toast.makeText(applicationContext, "Result saved!", Toast.LENGTH_LONG)
            toast.setGravity(Gravity.CENTER_VERTICAL, 0, 400)
            toast.show()//for random next question

        }
    }
}