package vcmsa.ci.historyapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class QuestionsScreen : AppCompatActivity() {

    //Declare and initialise the parallel array which are the questions and answers.
    private val questions = arrayOf(
        "The Great Wall Of China Was Not Built To Protect China From Intruders",
        "World War One Started In 1939",
        "The Roman Empire Fell In The Year 476 AD",
        "Christopher Columbus Found Australia In 1492",
        "Nelson Mandela Was South Africa's First Black President"
    )
    private val answers = arrayOf(false, true, true, false, true)

    //Declare all variables
    private lateinit var questionText: TextView
    private lateinit var answerText:TextView
    private lateinit var trueButton:Button
    private lateinit var falseButton:Button
    private lateinit var nextButton:Button

    //Declare all variables that will track the progress
    private var currentQuestionIndex = 0
    private var score = 0
    private var selectedAnswer = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_questions_screen)

        //declaring and initialising all variables
        questionText = findViewById(R.id.flashcardText)
        answerText = findViewById(R.id.answerText)
        trueButton = findViewById(R.id.trueButton)
        falseButton = findViewById(R.id.falseButton)
        nextButton = findViewById(R.id.nextButton)

        //hiding the answer and disabling the next button
        answerText.visibility = View.INVISIBLE
        nextButton.isEnabled = false

        //initialising all buttons
        trueButton.setOnClickListener {
            checkAnswer(true)
        }
        falseButton.setOnClickListener {
            checkAnswer(false)
        }
        nextButton.setOnClickListener {
            moveToTheFollowingQuestion()
        }
        displayQuestion()
    }
    private fun displayQuestion(){
        questionText.text = questions[currentQuestionIndex]
        answerText.visibility = View.INVISIBLE
        falseButton.isEnabled = true
        trueButton.isEnabled = false
        nextButton.isEnabled = false
        selectedAnswer = false
    }
    private fun checkAnswer(userAnswer: Boolean){
        if (selectedAnswer) return

        selectedAnswer = true
        val rightAnswer = answers[currentQuestionIndex]

        if (userAnswer == rightAnswer){
            answerText.text = "That Is Correct!"
        } else {
            answerText.text = "That Is Incorrect, Sorry!"
        }
        //enabling the next button and showing the feedback
        answerText.visibility = View.INVISIBLE
        trueButton.isEnabled = false
        falseButton.isEnabled = false
        nextButton.isEnabled = true
    }
    private fun moveToTheFollowingQuestion(){
        currentQuestionIndex++

        if (currentQuestionIndex < questions.size){
            displayQuestion()
        } else {
            //all answered questions are going to be moved to the score screen
            val intent = Intent(this, ScoreScreen::class.java)
            intent.putExtra("SCORE",score)
            intent.putExtra("The Total Question", questions.size)
            startActivity(intent)

            //this will close the question screen and move to the score screen
            finish()
        }
    }
}