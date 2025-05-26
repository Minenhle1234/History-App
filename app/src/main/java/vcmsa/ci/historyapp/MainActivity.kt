package vcmsa.ci.historyapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        //declare the variable for the button

        val startButton = findViewById<Button>(R.id.startButton)

        //set a set on click listener that will trigger the button to work

        startButton.setOnClickListener {
            val intent = Intent(this, QuestionsScreen::class.java)

            //This will trigger the screen to move to the next screen.
            startActivity(intent)
        }

    }
}