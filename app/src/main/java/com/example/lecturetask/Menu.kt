package com.example.lecturetask

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.example.lecturetask.databinding.ActivityResultBinding

class Menu : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.menu)
        val startButton = findViewById<Button>(R.id.buttonStart)
        startButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }


        val viewButton = findViewById<Button>(R.id.buttonResult)
        viewButton.setOnClickListener {
            val intent = Intent(this, Results::class.java)
            startActivity(intent)
        }

    }
}