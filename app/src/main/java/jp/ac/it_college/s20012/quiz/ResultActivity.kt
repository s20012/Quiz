package jp.ac.it_college.s20012.quiz

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class ResultActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        val answer = intent.getIntExtra("ANSWER",0)
        val yes: TextView = findViewById(R.id.textView)
        val id = answer.toString()

        yes.text = "１０問中..." + id + "問正解！"
    }
}