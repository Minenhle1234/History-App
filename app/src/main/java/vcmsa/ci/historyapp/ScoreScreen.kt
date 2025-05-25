package vcmsa.ci.historyapp

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ScoreScreen : AppCompatActivity() {
    private lateinit var scoreText: TextView
    private lateinit var feedbackText:TextView
    private lateinit var reviewButton:Button
    private lateinit var exitButton:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_score_screen)


        //Initialise all all variables.
        scoreText = findViewById(R.id.ScoreText)
        feedbackText = findViewById(R.id.feedbackText)
        reviewButton = findViewById(R.id.ReviewButton)
        exitButton = findViewById(R.id.exitButton)

        //getting the score from the intent
        val score = intent.putExtra("SCORE",0)

        //This is the total fixed number of questions
        val totalQuestions = 5

        //Display the score
        scoreText.text = "You Got $score Out Of $totalQuestions Correct!"


    }
}