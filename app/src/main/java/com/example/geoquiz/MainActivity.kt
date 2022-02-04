package com.example.geoquiz


import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast

private const val TAG ="MainActivity"

class MainActivity : AppCompatActivity() {
    private lateinit var  trueButton: Button
    private lateinit var  falseButton: Button
    private lateinit var  nextButton: ImageButton
    private lateinit var prevButton:ImageButton
    private  lateinit var questionTextView: TextView
    var score:TextView? = null
    private val questionBank = listOf(
        Question(R.string.question_australia,true),
        Question(R.string.question_oceans,true),
        Question(R.string.question_vanya,true),
        Question(R.string.question_bekachad,true),
        Question(R.string.question_kama,true),
        Question(R.string.question_mideast,false),
        Question(R.string.question_africa,false),
        Question(R.string.question_americas,true),
        Question(R.string.question_asia,true),
        Question(R.string.ending,true))
    private var currentIndex = -1
    private var currentPoint = 0
    var textScore = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG,"onCreate(Bundle?) called")
        setContentView(R.layout.activity_main)
        trueButton = findViewById(R.id.true_button)
        falseButton = findViewById(R.id.false_button)
        nextButton = findViewById(R.id.next_button)
        prevButton = findViewById(R.id.prev_button)
        score = findViewById(R.id.score)
        score?.text = "High score: 0%"
        questionTextView = findViewById(R.id.question_text_view)
        questionTextView.setOnClickListener{updateQuestion()}
        trueButton.setOnClickListener{view: View ->
               checkAnswer(true)
               updateQuestion()
               textScore = 100*currentPoint/questionBank.size
               score?.text = "High score: ${textScore}%"
        }
        falseButton.setOnClickListener{view: View ->
                checkAnswer(false)
                textScore = 100*currentPoint/questionBank.size
                score?.text = "High score: ${textScore}%"
                updateQuestion()
        }
        nextButton.setOnClickListener{
            updateQuestion()
        }
        prevButton.setOnClickListener{
            if (currentIndex > 0){
                currentIndex = currentIndex - 2
                updateQuestion()
            }
        }
        updateQuestion()
    }
    override fun onStart(){
        super.onStart()
        Log.d(TAG,"onStart() called")

    }
    override fun onResume(){
        super.onResume()
        Log.d(TAG,"onResume() called")

    }
    override fun onPause(){
        super.onPause()
        Log.d(TAG,"onPause() called")
    }
    override fun onStop(){
        super.onStop()
        Log.d(TAG,"onStop() called")
    }
    override fun onDestroy(){
        super.onDestroy()
        Log.d(TAG,"OnDestroy() called")
    }
    private fun updateQuestion() {
        if (currentIndex == questionBank.size-1) return
        currentIndex += 1
        val questionTextResId = questionBank[currentIndex].textResId
        questionTextView.setText(questionTextResId)
    }
    private fun checkAnswer(userAnswer: Boolean){
        val correctAnswer = questionBank[currentIndex].answer
        var messageResId = R.string.incorrect_toast
        if (currentIndex  > questionBank.size - 2) return
        if (userAnswer == correctAnswer){
            messageResId = R.string.correct_toast
            currentPoint = currentPoint + 1
        }
        Toast.makeText(this,messageResId,Toast.LENGTH_SHORT).show()
    }
}