package vcmsa.ci.historyapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class ScoreScreen : AppCompatActivity() {

    private lateinit var scoreText: TextView
    private lateinit var feedbackText: TextView
    private lateinit var reviewButton: Button
    private lateinit var exitButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_score_screen)

        // Link UI components
        scoreText = findViewById(R.id.scoreText)
        feedbackText = findViewById(R.id.feedbackText)
        reviewButton = findViewById(R.id.ReviewButton)
        exitButton = findViewById(R.id.exitButton)

        // Title:How do I handle errors in Kotlin arrows
        // Author: pbuchheit
        // Date: 31 July 2024
        // Version: 1.0
        // Available:https://stackoverflow.com/questions/78817291/how-do-i-handle-logical-errors-in-kotlin-arrow

        // validation: check if the input is correctly filled
        // Receive data from the quiz
        val score = intent.getIntExtra("SCORE", 0)
        val totalQuestions = 5

        val questions = intent.getStringArrayExtra("QUESTIONS") ?: arrayOf()
        val answers = intent.getStringArrayExtra("ANSWERS") ?: arrayOf()
        val userAnswers = intent.getBooleanArrayExtra("USER_ANSWERS") ?: booleanArrayOf()

        // Display score
        scoreText.text = "You got $score out of $totalQuestions correct!"

        // Personalized feedback
        if (score > 3) {
            feedbackText.text = "GREAT JOB!"
            feedbackText.setTextColor(ContextCompat.getColor(this, android.R.color.holo_blue_dark))
        } else {
            feedbackText.text = "KEEP PRACTICING!"
            feedbackText.setTextColor(ContextCompat.getColor(this, android.R.color.holo_red_dark))
        }

        // Launch ReviewScreen
        reviewButton.setOnClickListener {
            val intent = Intent(this, ReviewScreen::class.java)
            intent.putExtra("QUESTIONS", questions)
            intent.putExtra("ANSWERS", answers)
            intent.putExtra("USER_ANSWERS", userAnswers)
            startActivity(intent)
        }

        // Exit button closes the app
        exitButton.setOnClickListener {
            finishAffinity()
        }
    }
}



















