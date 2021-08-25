package jp.ac.it_college.s20012.quiz

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
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

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question)

        //viewの定義
        val tvCount: TextView = findViewById(R.id.tvCount)
        val tvQuestion: TextView = findViewById(R.id.tvQuestion)
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
            data[0],  //１問目
            data[7],  //２問目
            data[14], //３問目
            data[21], //４問目
            data[28], //５問目
            data[35], //６問目
            data[42], //７問目
            data[49], //８問目
            data[56], //９問目
            data[63]  //１０問目
        )

        //選択肢データ
        val choicesData = arrayOf(
            arrayOf(data[0 + 2], data[0 + 3], data[0 + 4], data[0 + 5]),     //１問目
            arrayOf(data[7 + 2], data[7 + 3], data[7 + 4], data[7 + 5]),     //２問目
            arrayOf(data[14 + 2], data[14 + 3], data[14 + 4], data[14 + 5]), //３問目
            arrayOf(data[21 + 2], data[21 + 3], data[21 + 4], data[21 + 5]), //４問目
            arrayOf(data[28 + 2], data[28 + 3], data[28 + 4], data[28 + 5]), //５問目
            arrayOf(data[35 + 2], data[35 + 3], data[35 + 4], data[35 + 5]), //６問目
            arrayOf(data[42 + 2], data[42 + 3], data[42 + 4], data[42 + 5]), //７問目
            arrayOf(data[49 + 2], data[49 + 3], data[49 + 4], data[49 + 5]), //８問目
            arrayOf(data[56 + 2], data[56 + 3], data[56 + 4], data[56 + 5]), //９問目
            arrayOf(data[63 + 2], data[63 + 3], data[63 + 4], data[63 + 5])  //１０問目
        )

        //カウント数と、最初の問題表示
        tvCount.text = "第1問"
        tvQuestion.text = titleData[0]

        //選択肢シャッフル
        val list = listOf(0, 1, 2, 3)
        val num = list.shuffled()

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

            }else{
                //不正解
                incorrectAns()
            }
        }

        //10）btn1～3も同じようにする
        btn1.setOnClickListener {
            if(btn1.text == choicesData[i][0]){
                //正解
                correctAns()
            }else{
                //不正解
                incorrectAns()
            }
        }

        btn2.setOnClickListener {
            if(btn2.text == choicesData[i][0]){
                //正解
                correctAns()
            }else{
                //不正解
                incorrectAns()
            }
        }

        btn3.setOnClickListener {
            if(btn3.text == choicesData[i][0]){
                //正解
                correctAns()
            }else{
                //不正解
                incorrectAns()
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
            data[0],  //１問目
            data[7],  //２問目
            data[14], //３問目
            data[21], //４問目
            data[28], //５問目
            data[35], //６問目
            data[42], //７問目
            data[49], //８問目
            data[56], //９問目
            data[63]  //１０問目
        )

        //選択肢データ
        val choicesData = arrayOf(
            arrayOf(data[0 + 2], data[0 + 3], data[0 + 4], data[0 + 5]),     //１問目
            arrayOf(data[7 + 2], data[7 + 3], data[7 + 4], data[7 + 5]),     //２問目
            arrayOf(data[14 + 2], data[14 + 3], data[14 + 4], data[14 + 5]), //３問目
            arrayOf(data[21 + 2], data[21 + 3], data[21 + 4], data[21 + 5]), //４問目
            arrayOf(data[28 + 2], data[28 + 3], data[28 + 4], data[28 + 5]), //５問目
            arrayOf(data[35 + 2], data[35 + 3], data[35 + 4], data[35 + 5]), //６問目
            arrayOf(data[42 + 2], data[42 + 3], data[42 + 4], data[42 + 5]), //７問目
            arrayOf(data[49 + 2], data[49 + 3], data[49 + 4], data[49 + 5]), //８問目
            arrayOf(data[56 + 2], data[56 + 3], data[56 + 4], data[56 + 5]), //９問目
            arrayOf(data[63 + 2], data[63 + 3], data[63 + 4], data[63 + 5])  //１０問目
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
            val intent = Intent(this,ResultActivity::class.java)
            intent.putExtra("ANSWER", s)
            startActivity(intent)
            finish()
        }else{
            ++s
            //8)正解アラートダイアログ
            AlertDialog.Builder(this)
                .setTitle("正解！")
                .setPositiveButton("OK") { _, _ ->
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
            val intent = Intent(this, ResultActivity::class.java)
            intent.putExtra("ANSWER", s)
            startActivity(intent)
            finish()
        } else {
            //8)正解アラートダイアログ
            AlertDialog.Builder(this)
                .setTitle("不正解...")
                .setPositiveButton("OK") { _, _ ->
                    next()
                }
                .show()



        }
    }
}