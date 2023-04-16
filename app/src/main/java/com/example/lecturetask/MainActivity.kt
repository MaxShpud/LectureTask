package com.example.lecturetask

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.view.Gravity
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.lecturetask.databinding.ActivityMainBinding
import com.google.android.material.button.MaterialButton

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var rightAnswer: String? = null
    private var rightAnswerCounter: Int = 0
    private var quizCounter: Int = 1

    private val QUIZ_COUNT: Int = 4
    private val QUIZ_TIME: Long = 120000

    private val quizData = mutableListOf(
        mutableListOf("Choose the capital of Germany", "Berlin", "Munich", "Bangkok", "Tokio"),
        mutableListOf("Choose the capital of Belarus", "Minsk", "Moscow", "London", "Brest"),
        mutableListOf("Choose the capital of England", "London", "Washington DC", "New York", "Kiev"),
        mutableListOf("Choose the capital of Ukraine", "Kiev", "Bern", "Lvov", "Jakarta")
    )
    private lateinit var timer: CountDownTimer


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        val toast = Toast.makeText(applicationContext, "Time 2 minutes!", Toast.LENGTH_LONG)
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 680)
        toast.show()//for random next question
        quizData.shuffle()


        showNextQuestion()
        timer = object : CountDownTimer(QUIZ_TIME, 1000) {
            override fun onTick(millisUntilFinished: Long) {

                binding.timer.text = "Time left: ${millisUntilFinished / 1000}"
            }

            override fun onFinish() {
                binding.timer.text = "Time's up!"
                showResults()
            }
        }.start()
    }

    private fun showResults() {
        AlertDialog.Builder(this)
            .setTitle("Time's up!")
            .setMessage("You didn't finish the quiz in time.")
            .setPositiveButton("OK") { _, _ ->
                val intent = Intent(this, ResultActivity::class.java)
                intent.putExtra("counter", "The number of correct answers $rightAnswerCounter / 4")
                startActivity(intent)
            }
            .setCancelable(false)
            .show()
    }

    private fun showNextQuestion() {

        binding.numberQuestion.text = getString(R.string.count_label, quizCounter)
        val quiz = quizData[0]

        //set right answer and question
        binding.question.text = quiz[0]
        rightAnswer = quiz[1]

        // remote country
        quiz.removeAt(0)

        //random answer and choices
        quiz.shuffle()

        binding.firstAnswerBtn.text = quiz[0]
        binding.secondAnswerBtn.text = quiz[1]
        binding.thirdAnswerBtn.text = quiz[2]
        binding.fourthAnswerBtn.text = quiz[3]

        //remote this quiz from quizData
        quizData.removeAt(0)
    }


    private fun checkQuestionCount() {

        if (quizCounter == QUIZ_COUNT) {
            val intent = Intent(this, ResultActivity::class.java)
            intent.putExtra("counter", "The number of correct answers $rightAnswerCounter / 4")
            startActivity(intent)
        } else {
            quizCounter++
            showNextQuestion()
        }
    }

    fun checkAnswer(view: View) {
        val answerBtn: MaterialButton = findViewById(view.id)
        val btnText = answerBtn.text.toString()

        val alertTitle: String
        if (btnText == rightAnswer) {
            alertTitle = "Correct!"
            rightAnswerCounter++
        } else {
            alertTitle = "Wrong!"
        }

        AlertDialog.Builder(this)
            .setTitle(alertTitle)
            .setMessage("Answer: $rightAnswer")
            .setPositiveButton("OK") { _, _ ->
                checkQuestionCount()
            }
            .setCancelable(false)
            .show()
    }
}