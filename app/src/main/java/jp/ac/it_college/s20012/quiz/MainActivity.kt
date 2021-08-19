package jp.ac.it_college.s20012.quiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.opencsv.CSVIterator
import com.opencsv.CSVReader
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.StringReader


class MainActivity : AppCompatActivity() {


    //csvのデータをここにいれる
    private var data : ArrayList<String> = arrayListOf()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //viewの定義
        val tvQuestion :TextView =findViewById(R.id.tvquestion)


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

        val titleData : ArrayList<String> = arrayListOf(data[0 + 2],data[7],data[14])

        tvQuestion.text = titleData[0]
        println(titleData)



    }
}