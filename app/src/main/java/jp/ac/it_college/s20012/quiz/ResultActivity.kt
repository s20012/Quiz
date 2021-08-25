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

        //正解数取得
        val answer = intent.getIntExtra("ANSWER",0)
        val id = answer.toString()

        //viewの定義
        val yes: TextView = findViewById(R.id.textView)
        val se : TextView = findViewById(R.id.selfText)

        yes.text = "$id / 10"

        //正解数によってセリフを用意
        if(answer == 10) {
            se.text = "(｀・ω・´)"
        } else if(answer >= 7)  {
            se.text = "(・∀・)"
        } else if(answer >= 4) {
            se.text = "(；ー_ー)ノ"
        } else {
            se.text = "(´・ω・｀)"
        }



    }
}