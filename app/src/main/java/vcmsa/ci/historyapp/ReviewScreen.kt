package vcmsa.ci.historyapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ReviewScreen : AppCompatActivity() {
    private lateinit var exitButton: Button
    private lateinit var reviewContainer: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_review_screen)

        exitButton = findViewById(R.id.exitButton)
        reviewContainer = findViewById(R.id.reviewContainer)

        val questions = intent.getStringArrayExtra("QUESTIONS")
        val answers = intent.getStringArrayExtra("ANSWERS")
        val userAnswers = intent.getBooleanArrayExtra("USER_ANSWERS")

        if (questions != null && answers != null && userAnswers != null) {
            for (i in questions.indices) {
                val questionView = TextView(this).apply {
                    text = "Q${i + 1}: ${questions[i]}"
                    textSize = 16f
                }

                val answerView = TextView(this).apply {
                    text = "Correct Answer: ${answers[i]}"
                    setTextColor(resources.getColor(android.R.color.holo_green_dark, theme))
                }

                val userAnswerView = TextView(this).apply {
                    val result = if (userAnswers[i]) "Correct" else "Incorrect"
                    text = "Your Answer: $result"
                    setTextColor(
                        resources.getColor(
                            if (userAnswers[i]) android.R.color.holo_green_dark else android.R.color.holo_red_dark,
                            theme
                        )
                    )
                }

                reviewContainer.addView(questionView)
                reviewContainer.addView(answerView)
                reviewContainer.addView(userAnswerView)

                // Add spacing
                val spacer = TextView(this)
                spacer.text = "\n"
                reviewContainer.addView(spacer)
            }
        }

        exitButton.setOnClickListener {
            finish()
        }
    }
}