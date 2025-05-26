package vcmsa.ci.historyapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class QuestionsScreen : AppCompatActivity() {

    private val questions = arrayOf(
        "The Great Wall Of China Was Not Built To Protect China From Intruders",
        "World War One Started In 1939",
        "The Roman Empire Fell In The Year 476 AD",
        "Christopher Columbus Found Australia In 1492",
        "Nelson Mandela Was South Africa's First Black President"
    )

    private val answers = arrayOf(false, false, true, false, true)
    private val userAnswers = BooleanArray(questions.size)

    private var currentQuestionIndex = 0
    private var score = 0

    // UI Elements
    private lateinit var questionText: TextView
    private lateinit var trueButton: Button
    private lateinit var falseButton: Button
    private lateinit var nextButton: Button
    private lateinit var answerText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_questions_screen)

        // Link UI elements
        questionText = findViewById(R.id.questionText)
        trueButton = findViewById(R.id.trueButton)
        falseButton = findViewById(R.id.falseButton)
        nextButton = findViewById(R.id.nextButton)
        answerText = findViewById(R.id.answerText)

        showQuestion()

        trueButton.setOnClickListener {
            checkAnswer(true)
        }

        falseButton.setOnClickListener {
            checkAnswer(false)
        }

        nextButton.setOnClickListener {
            currentQuestionIndex++
            if (currentQuestionIndex < questions.size) {
                showQuestion()
                enableButtons(true)
            } else {
                goToScoreScreen()
            }
        }
    }

    private fun showQuestion() {
        questionText.text = questions[currentQuestionIndex]
        answerText.text = "" // Clear previous feedback
        nextButton.visibility = View.GONE
    }

    private fun checkAnswer(userAnswer: Boolean) {
        val correctAnswer = answers[currentQuestionIndex]
        val isCorrect = userAnswer == correctAnswer

        userAnswers[currentQuestionIndex] = isCorrect

        if (isCorrect) {
            score++
            answerText.text = "✅ Correct!"
            answerText.setTextColor(ContextCompat.getColor(this, android.R.color.holo_green_dark))
        } else {
            answerText.text = "❌ Incorrect"
            answerText.setTextColor(ContextCompat.getColor(this, android.R.color.holo_red_dark))
        }

        enableButtons(false)
        nextButton.visibility = View.VISIBLE
    }

    private fun enableButtons(enable: Boolean) {
        trueButton.isEnabled = enable
        falseButton.isEnabled = enable
    }

    private fun goToScoreScreen() {
        val intent = Intent(this, ScoreScreen::class.java)
        intent.putExtra("SCORE", score)
        intent.putExtra("QUESTIONS", questions)
        intent.putExtra("ANSWERS", answers.map { it.toString() }.toTypedArray())
        intent.putExtra("USER_ANSWERS", userAnswers)
        startActivity(intent)
        finish()
    }
}