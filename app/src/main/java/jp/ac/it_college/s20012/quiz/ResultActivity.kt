package jp.ac.it_college.s20012.quiz

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class ResultActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        //正解数取得
        val answer = intent.getIntExtra("ANSWER",0)
        val time = intent.getStringExtra("TIME")
        val id = answer.toString()

        //viewの定義
        val yes: TextView = findViewById(R.id.textView)
        val se : TextView = findViewById(R.id.selfText)
        val back : Button = findViewById(R.id.homeButton)
        val no : TextView = findViewById(R.id.okTime)

        yes.text = "$id / 10"

        no.text = time

        //正解数によってセリフを用意
        if(answer == 10) {
            se.text = "パーフェクト！！！"
        } else if(answer >= 7)  {
            se.text = "あともう少し！"
        } else if(answer >= 4) {
            se.text = "まぁまぁかな..."
        } else {
            se.text = "(´・ω・｀)"
        }


        //最初の画面へ戻る
        back.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

    }
}