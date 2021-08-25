package jp.ac.it_college.s20012.quiz

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val title: Button = findViewById(R.id.button)

        //ボタンを押すとクイズが始まる
        title.setOnClickListener {
            val intent = Intent(this, Question::class.java)
            startActivity(intent)
        }

    }
}
