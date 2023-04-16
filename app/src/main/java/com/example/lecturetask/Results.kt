package com.example.lecturetask


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import java.io.BufferedReader
import java.io.FileOutputStream


class Results : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.results)
        val result = findViewById<TextView>(R.id.resultEnd)
        try {
            val fileInput = openFileInput("test_results.txt")
            val fileContents = fileInput.bufferedReader().use(BufferedReader::readText)
            result.text = fileContents
            fileInput.close()
        } catch (e: java.lang.Exception) {
            Log.e("ResultListActivity", "Error reading file", e)
            result.text = "Error reading file"
        }
        val buttonClear = findViewById<Button>(R.id.clear_records)
        buttonClear.setOnClickListener {
            val filename = "test_results.txt"
            val fileOutput = FileOutputStream(filesDir.toString() + "/" + filename)
            fileOutput.write("".toByteArray())
            fileOutput.close()

        }
    }
}