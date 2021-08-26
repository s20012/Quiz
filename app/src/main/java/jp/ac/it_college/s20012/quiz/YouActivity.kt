package jp.ac.it_college.s20012.quiz

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.TextView
import androidx.core.view.ViewCompat
import java.util.logging.Handler

class YouActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_you)

        val ti = MyCountDownTimer(5000, 1000)

        ti.start()
    }

    inner class MyCountDownTimer(
        millisInFuture: Long,
        countDownInterval: Long
    ) : CountDownTimer(millisInFuture, countDownInterval) {

        private val timer: TextView = findViewById(R.id.youText)



        @SuppressLint("SetTextI18n")
        override fun onTick(millisUntilFinished: Long) {
            val second = kotlin.math.ceil(millisUntilFinished / 1000.0).toInt()
            timer.text = "%1$01d".format(second)

            timer.scaleX = 1f
            timer.scaleY = 1f

            ViewCompat.animate(timer)
                .setDuration(1000)
                .scaleX(2f)
                .scaleY(2f)
                .start()
        }
        @SuppressLint("SetTextI18n")
        override fun onFinish() {
            timer.text = "GO!!!!"
            android.os.Handler().postDelayed({ val intent = Intent(this@YouActivity,Question::class.java)
                startActivity(intent)},1000)

        }
    } }