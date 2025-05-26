package vcmsa.ci.historyapp

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class ScoreScreen : AppCompatActivity() {

    // Title: History Quiz App QuestionScreen
    // Author: Developer
    // Date: 20 May 2025
    // Version: 1.0
    // Available:https://developer.android.com/reference/android/app/Activity
    // Additional References:https://developer.android.com/reference/android/widget/variables


    private lateinit var scoreText: TextView
    private lateinit var feedbackText: TextView
    private lateinit var reviewButton: Button
    private lateinit var exitButton: Button


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_score_screen)

        // Initialise all variables
        scoreText = findViewById(R.id.scoreText)
        feedbackText = findViewById(R.id.feedbackText)
        reviewButton = findViewById(R.id.ReviewButton)
        exitButton = findViewById(R.id.exitButton)

        // Get the score passed from the previous activity
        val score = intent.getIntExtra("SCORE", 0)

        // This is the total fixed number of questions
        val totalQuestions = 5

        // Display the score
        scoreText.text = "You got $score out of $totalQuestions correct!"

        // Show the personalised feedback
        if (score > 3) {
            feedbackText.text = "GREAT JOB!"
            feedbackText.setTextColor(ContextCompat.getColor(this, android.R.color.holo_blue_dark))
        } else {
            feedbackText.text = "KEEP PRACTICING!"
            feedbackText.setTextColor(ContextCompat.getColor(this, android.R.color.holo_red_dark))
        }

        // Set listener for the Review Button
        reviewButton.setOnClickListener {
            // TODO: Navigate to Review Screen
        }

    }
}



















