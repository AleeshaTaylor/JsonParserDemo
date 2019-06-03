package exam.aleeshataylor.jsonparserdemo.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import exam.aleeshataylor.jsonparserdemo.entity.Sentence
import exam.aleeshataylor.jsonparserdemo.util.GetJsonUtil
import exam.aleeshataylor.jsonparserdemo.util.ParserJson
import kotlinx.android.synthetic.main.activity_daily_sentence.*
import java.text.SimpleDateFormat
import java.util.*


class DailySentenceActivity : AppCompatActivity() {

    @SuppressLint("SimpleDateFormat", "SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(exam.aleeshataylor.jsonparserdemo.R.layout.activity_daily_sentence)

        //获取系统时间
        val format = SimpleDateFormat("HH:mm:ss")
        val date = Date()
        val time = format.format(date)


        //获取零点整
        val nowTime = System.currentTimeMillis()
        val todayStartTime = nowTime - (nowTime + TimeZone.getDefault().rawOffset) % (1000 * 3600 * 24)
        val tagTime = format.format(todayStartTime)
        Log.i("时间",tagTime)

        //获得每日一句的JSON数据的字符串对象
        val jsonArr: String = GetJsonUtil.getJson("sentence.json",this)
        //实例化ParserJson类
        val parserJson = ParserJson()
        //获得每日一句的JSON数据的数组对象
        val ss: List<Sentence> = parserJson.parserArrayForSentence(jsonArr)
        txt_daily.text = ss[0].toString()

//        val ss: List<Sentence> = parserJson.parserArrayToSentence(jsonArr)
//        showJSONArrayData(ss)

        if (time == tagTime){
            for (i in 0 until ss.size) {
                if (ss[i] === txt_daily.text) {
                    txt_daily.text = ss[i+1].toString()
                }
            }
        }

    }

    /**
     * 显示JSON数组的值
     * @param type 当前用什么类型解析
     * @param ps 要显示数据的对象数组
     */
    @SuppressLint("SetTextI18n")
    fun showJSONArrayData(ss: List<Sentence>) {
        for (s in ss) {
            txt_daily.append(s.toString() + "\n")
        }
    }
}
