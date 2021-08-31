package jp.ac.it_college.s20012.quiz

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.opencsv.CSVIterator
import com.opencsv.CSVReader
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.StringReader

class Question : AppCompatActivity() {
    //csvのデータをここにいれる
    private var data : ArrayList<String> = arrayListOf()

    //カウント変数を用意
    private var i = 0

    //正解数変数を用意
    private var s = 0

    val handler = Handler(Looper.getMainLooper())
    var timeValue = 0

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question)

        //viewの定義
        val tvCount: TextView = findViewById(R.id.tvCount)
        val tvQuestion: TextView = findViewById(R.id.tvQuestion)
        val countDown: ProgressBar = findViewById(R.id.progress)
        val timer: TextView = findViewById(R.id.timerText)
        val btn0: Button = findViewById(R.id.btn0)
        val btn1: Button = findViewById(R.id.btn1)
        val btn2: Button = findViewById(R.id.btn2)
        val btn3: Button = findViewById(R.id.btn3)

        //取得したcsvデータを配列dataに入れる
        val assetManager = resources.assets
        val inputStream = assetManager.open("data.csv")
        val bufferedReader = BufferedReader(InputStreamReader(inputStream))
        val str: String = bufferedReader.readText()
        val strReader = StringReader(str)
        val csv = CSVIterator(CSVReader(strReader))

        for (row in csv) {
            for (col in row) {
                data.add(col)
            }
        }
        //ここまで

        //問題文データ
        val titleData: ArrayList<String> = arrayListOf(
            data[7],  //１問目
            data[14],  //２問目
            data[21], //３問目
            data[28], //４問目
            data[35], //５問目
            data[42], //６問目
            data[49], //７問目
            data[56], //８問目
            data[63], //９問目
            data[70]  //１０問目
        )

        //選択肢データ
        val choicesData = arrayOf(
            arrayOf(data[7 + 2], data[7 + 3], data[7 + 4], data[7 + 5]),     //1問目
            arrayOf(data[14 + 2], data[14 + 3], data[14 + 4], data[14 + 5]), //2問目
            arrayOf(data[21 + 2], data[21 + 3], data[21 + 4], data[21 + 5]), //3問目
            arrayOf(data[28 + 2], data[28 + 3], data[28 + 4], data[28 + 5]), //4問目
            arrayOf(data[35 + 2], data[35 + 3], data[35 + 4], data[35 + 5]), //5問目
            arrayOf(data[42 + 2], data[42 + 3], data[42 + 4], data[42 + 5]), //6問目
            arrayOf(data[49 + 2], data[49 + 3], data[49 + 4], data[49 + 5]), //7問目
            arrayOf(data[56 + 2], data[56 + 3], data[56 + 4], data[56 + 5]), //8問目
            arrayOf(data[63 + 2], data[63 + 3], data[63 + 4], data[63 + 5]), //9問目
            arrayOf(data[70 + 2], data[70 + 3], data[70 + 4], data[70 + 5]), //10問目
        )

        //カウント数と、最初の問題表示
        tvCount.text = "第1問"
        tvQuestion.text = titleData[0]

        countDown.max = 10
        val ti = MyCountDownTimer(10000, 1000)

        ti.start()

        //選択肢シャッフル
        val list = listOf(0, 1, 2, 3)
        val num = list.shuffled()
        fun count() {
            ti.isRunning = when (ti.isRunning) {
                true -> {
                    ti.cancel()
                    false
                }
                false -> {
                    ti.cancel()
                    true
                }
            }
            ti.start()
        }

        //buttonに選択肢を表示
        btn0.text = choicesData[0][num[0]]
        btn1.text = choicesData[0][num[1]]
        btn2.text = choicesData[0][num[2]]
        btn3.text = choicesData[0][num[3]]


        //６）btn0を押した時の正誤判定
        btn0.setOnClickListener {
            if(btn0.text == choicesData[i][0]){
                //正解
                correctAns()
                count()

            }else{
                //不正解
                incorrectAns()
                count()
            }
        }

        //10）btn1～3も同じようにする
        btn1.setOnClickListener {
            if(btn1.text == choicesData[i][0]){
                //正解
                correctAns()
                count()
            }else{
                //不正解
                incorrectAns()
                count()
            }
        }

        btn2.setOnClickListener {
            if(btn2.text == choicesData[i][0]){
                //正解
                correctAns()
                count()
            }else{
                //不正解
                incorrectAns()
                count()
            }
        }

        btn3.setOnClickListener {
            if(btn3.text == choicesData[i][0]){
                //正解
                correctAns()
                count()
            }else{
                //不正解
                incorrectAns()
                count()
            }
        }

        val runnable = object : Runnable {

            override fun run() {
                timeValue++                      // 秒カウンタ+1
                timeToText(timeValue)?.let {        // timeToText()で表示データを作り
                    timer.text = it            // timeText.textへ代入(表示)
                }
                handler.postDelayed(this, 1000)  // 1000ｍｓ後に自分にpost
            }

        }

        runnable.run()
        val time = timeToText().toString()
        val intent = Intent(this,ResultActivity::class.java)
                     intent.putExtra("TIME", time)

    }
    //ここから
    inner class MyCountDownTimer(
        millisInFuture: Long,
        countDownInterval: Long
    ) : CountDownTimer(millisInFuture, countDownInterval) {
        var isRunning = true

        private val countDown: ProgressBar = findViewById(R.id.progress)

        @SuppressLint("SetTextI18n")
        override fun onTick(millisUntilFinished: Long) {
            val second = kotlin.math.ceil(millisUntilFinished / 1000.0).toInt()
            countDown.progress = second

        }

        @SuppressLint("SetTextI18n")
        override fun onFinish() {
            countDown.progress = 0

            if(i == 9){
                AlertDialog.Builder(this@Question)
                    .setTitle("時間切れ")
                    .setPositiveButton("次へ") { _, _ ->
                        val intent = Intent(this@Question,ResultActivity::class.java)
                        intent.putExtra("ANSWER", s)
                        startActivity(intent)
                        finish()
                    }


            }else{
                //8)正解アラートダイアログ
                AlertDialog.Builder(this@Question)
                    .setTitle("時間切れ")
                    .setPositiveButton("次へ") { _, _ ->
                        next()
                        start()
                    }
                    .show()

            }

        }

    }
    //ここまで

    private fun timeToText(time: Int = 0): String? {
        return when {
            time < 0 -> {
                null    // 時刻が0未満の場合 null
            }
            time == 0 -> {
                "00:00"
            }
            else -> {
                val m = time % 3600 / 60
                val s = time % 60
                "%1$02d:%2$02d".format(m, s)  // 表示に整形
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun next() {
        val tvCount : TextView =findViewById(R.id.tvCount)
        val tvQuestion : TextView =findViewById(R.id.tvQuestion)
        val btn0: Button = findViewById(R.id.btn0)
        val btn1: Button = findViewById(R.id.btn1)
        val btn2: Button = findViewById(R.id.btn2)
        val btn3: Button = findViewById(R.id.btn3)

        val titleData: ArrayList<String> = arrayListOf(
            data[7],  //１問目
            data[14],  //２問目
            data[21], //３問目
            data[28], //４問目
            data[35], //５問目
            data[42], //６問目
            data[49], //７問目
            data[56], //８問目
            data[63], //９問目
            data[70]  //１０問目
        )

        //選択肢データ
        val choicesData = arrayOf(
            arrayOf(data[7 + 2], data[7 + 3], data[7 + 4], data[7 + 5]),     //1問目
            arrayOf(data[14 + 2], data[14 + 3], data[14 + 4], data[14 + 5]), //2問目
            arrayOf(data[21 + 2], data[21 + 3], data[21 + 4], data[21 + 5]), //3問目
            arrayOf(data[28 + 2], data[28 + 3], data[28 + 4], data[28 + 5]), //4問目
            arrayOf(data[35 + 2], data[35 + 3], data[35 + 4], data[35 + 5]), //5問目
            arrayOf(data[42 + 2], data[42 + 3], data[42 + 4], data[42 + 5]), //6問目
            arrayOf(data[49 + 2], data[49 + 3], data[49 + 4], data[49 + 5]), //7問目
            arrayOf(data[56 + 2], data[56 + 3], data[56 + 4], data[56 + 5]), //8問目
            arrayOf(data[63 + 2], data[63 + 3], data[63 + 4], data[63 + 5]), //9問目
            arrayOf(data[70 + 2], data[70 + 3], data[70 + 4], data[70 + 5]), //10問目
        )

        val list = listOf(0, 1, 2, 3)

        i++
        //もう1回シャッフル
        val numNext = list.shuffled()


        //i問目のタイトルと問題を表示
        tvCount.text = "第" + (i + 1) + "問"
        tvQuestion.text = titleData[i]
        btn0.text = choicesData[i][numNext[0]]
        btn1.text = choicesData[i][numNext[1]]
        btn2.text = choicesData[i][numNext[2]]
        btn3.text = choicesData[i][numNext[3]]
        //ボタンを有効化する
        btn0.isEnabled = true
        btn1.isEnabled = true
        btn2.isEnabled = true
        btn3.isEnabled = true
    }

    //９）正解の関数
    private fun correctAns(){

        //12)全問正解で結果画面へ
        if(i == 9){
            ++s
            AlertDialog.Builder(this)
                .setTitle("正解！")
                .setPositiveButton("次へ") { _, _ ->
                    val intent = Intent(this,ResultActivity::class.java)
                    intent.putExtra("ANSWER", s)
                    startActivity(intent)
                    finish()
                }
                .show()

        }else{
            ++s
            //8)正解アラートダイアログ
            AlertDialog.Builder(this)
                .setTitle("正解！")
                .setPositiveButton("次へ") { _, _ ->
                    next()
                }
                .show()

        }

    }

    //９）不正解の関数
    @SuppressLint("SetTextI18n")
    fun incorrectAns() {

        //12)全問正解で結果画面へ
        if (i == 9) {
            AlertDialog.Builder(this)
                .setTitle("不正解...")
                .setPositiveButton("次へ") { _, _ ->
                    val intent = Intent(this, ResultActivity::class.java)
                    intent.putExtra("ANSWER", s)
                    startActivity(intent)
                    finish()
                }
                .show()

        } else {
            //8)正解アラートダイアログ
            AlertDialog.Builder(this)
                .setTitle("不正解...")
                .setPositiveButton("次へ") { _, _ ->
                    next()
                }
                .show()



        }
    }
}