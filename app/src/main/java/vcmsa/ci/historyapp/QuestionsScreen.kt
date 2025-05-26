package vcmsa.ci.historyapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class QuestionsScreen : AppCompatActivity() {

    // Title: History Quiz App QuestionScreen
    // Author: Developer
    // Date: 20 May 2025
    // Version: 1.0
    // Available:https://developer.android.com/reference/android/app/Activity
    // Additional References:https://developer.android.com/reference/android/widget/variables

    // Declare and initialize the parallel arrays: questions and answers
    private val questions = arrayOf(
        "The Great Wall Of China Was Not Built To Protect China From Intruders",
        "World War One Started In 1939",
        "The Roman Empire Fell In The Year 476 AD",
        "Christopher Columbus Found Australia In 1492",
        "Nelson Mandela Was South Africa's First Black President"
    )
    private val answers = arrayOf(false, true, true, false, true)

    // Declare UI elements
    private lateinit var questionText: TextView
    private lateinit var answerText: TextView
    private lateinit var trueButton: Button
    private lateinit var falseButton: Button
    private lateinit var nextButton: Button

    // Track progress
    private var currentQuestionIndex = 0
    private var score = 0
    private var selectedAnswer = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_questions_screen)

        // Initialize all view elements
        questionText = findViewById(R.id.flashcardText)
        answerText = findViewById(R.id.answerText)
        trueButton = findViewById(R.id.trueButton)
        falseButton = findViewById(R.id.falseButton)
        nextButton = findViewById(R.id.nextButton)

        // Title: History Quiz App QuestionScreen
        // Author: Developer
        // Date: 22 May 2025
        // Version: 1.0
        // Available:https://developer.android.com/reference/android/app/Activity
        // Additional References:https://developer.android.com/reference/android/widget/Button

        // Hide answerText initially and disable the next button
        answerText.visibility = View.INVISIBLE
        nextButton.isEnabled = false

        // Set up button listeners
        trueButton.setOnClickListener {
            checkAnswer(true)
        }

        falseButton.setOnClickListener {
            checkAnswer(false)
        }

        nextButton.setOnClickListener {
            moveToTheFollowingQuestion()
        }

        // Show the first question
        displayQuestion()
    }

    private fun displayQuestion() {
        questionText.text = questions[currentQuestionIndex]
        answerText.visibility = View.INVISIBLE
        trueButton.isEnabled = true
        falseButton.isEnabled = true
        nextButton.isEnabled = false
        selectedAnswer = false
    }

    // Title: Kotlin If ... Else
    // Author: w3schools
    // Date: 20 May 2025
    // Version: 1.0
    // Available: https://www.w3schools.com/kotlin/kotlin_conditions.php

    private fun checkAnswer(userAnswer: Boolean) {
        if (selectedAnswer) return

        selectedAnswer = true
        val rightAnswer = answers[currentQuestionIndex]

        if (userAnswer == rightAnswer) {
            answerText.text = "That Is Correct!"
            score++
        } else {
            answerText.text = "That Is Incorrect, Sorry!"
        }

        // Show feedback and allow next question
        answerText.visibility = View.VISIBLE
        trueButton.isEnabled = false
        falseButton.isEnabled = false
        nextButton.isEnabled = true
    }

    private fun moveToTheFollowingQuestion() {
        currentQuestionIndex++

        if (currentQuestionIndex < questions.size) {
            displayQuestion()
        } else {
            val intent = Intent(this, ScoreScreen::class.java)
            intent.putExtra("SCORE", score)
            intent.putExtra("The Total Question", questions.size)
            startActivity(intent)
            finish()
        }
    }
}