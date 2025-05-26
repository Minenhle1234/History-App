package vcmsa.ci.historyapp

import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class ReviewScreen : AppCompatActivity() {

    private lateinit var exitButton: Button
    private lateinit var reviewContainer: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_review_screen)

       // initialise the variables
        exitButton = findViewById(R.id.exitButton)
        reviewContainer = findViewById(R.id.reviewContainer)

        // Title: How to set the review screen after an action has been completed?
        // Author: "Adil Sombrero" on Stack Overflow
        // Date: 12 November 2013
        // Version: 1.0
        // Available: https://stackoverflow.com/questions/7241808/how-to-reset-edittext-after-an-action-has-been-completed/7241832

        //declare the components that are used
        val questions = intent.getStringArrayExtra("QUESTIONS")
        val answers = intent.getStringArrayExtra("ANSWERS")
        val userAnswers = intent.getBooleanArrayExtra("USER_ANSWERS")

        //if statement for the review screen
        if (questions != null && answers != null && userAnswers != null) {
            for (i in questions.indices) {
                val questionView = TextView(this).apply {
                    text = "Q${i + 1}: ${questions[i]}"
                    textSize = 18f
                    setPadding(0, 16, 0, 4)
                }

                val answerView = TextView(this).apply {
                    text = "Correct Answer: ${answers[i]}"
                    textSize = 16f
                    setTextColor(ContextCompat.getColor(this@ReviewScreen, android.R.color.holo_green_dark))
                }

                val userAnswerView = TextView(this).apply {
                    val result = if (userAnswers[i]) "Correct" else "Incorrect"
                    text = "Your Answer: $result"
                    textSize = 16f
                    setTextColor(
                        ContextCompat.getColor(
                            this@ReviewScreen,
                            if (userAnswers[i]) android.R.color.holo_green_dark else android.R.color.holo_red_dark
                        )
                    )
                }

                // Add everything to the container
                reviewContainer.addView(questionView)
                reviewContainer.addView(answerView)
                reviewContainer.addView(userAnswerView)

                // Spacer
                val spacer = TextView(this)
                spacer.text = ""
                spacer.textSize = 8f
                reviewContainer.addView(spacer)
            }
        }

        exitButton.setOnClickListener {
            finish()
        }
    }
}