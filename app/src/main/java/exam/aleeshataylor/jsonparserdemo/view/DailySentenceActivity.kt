package exam.aleeshataylor.jsonparserdemo.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.support.v7.app.AppCompatActivity
import android.util.Log
import exam.aleeshataylor.jsonparserdemo.entity.Sentence
import exam.aleeshataylor.jsonparserdemo.util.GetJsonUtil
import exam.aleeshataylor.jsonparserdemo.util.ParserJson
import kotlinx.android.synthetic.main.activity_daily_sentence.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import java.text.SimpleDateFormat
import java.util.*

class DailySentenceActivity : AppCompatActivity() {
    private val CHANGE_TEXT = 1

    @SuppressLint("SimpleDateFormat", "SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(exam.aleeshataylor.jsonparserdemo.R.layout.activity_daily_sentence)

        //获取系统时间
        val format = SimpleDateFormat("HH:mm:ss")
        val date = Date()
        val time = format.format(date)
        Log.i("时间", time)


        //获取零点整
        val nowTime = System.currentTimeMillis()
        val todayStartTime = nowTime - (nowTime + TimeZone.getDefault().rawOffset) % (1000 * 3600 * 24)
        val tagTime = format.format(todayStartTime)
        Log.i("目标时间", tagTime)

        //获得每日一句的JSON数据的字符串对象
        val jsonArr: String = GetJsonUtil.getJson("sentence.json",this)
        //实例化ParserJson类
        val parserJson = ParserJson()
        //获得每日一句的JSON数据的数组对象
        val ss: List<Sentence> = parserJson.parserArrayForSentence(jsonArr)

        var temp: MutableList<Sentence>? = null
        var num = 0

        if (txt_daily.text == "每日一句"){
            txt_daily.text = ss[0].toString()
        }else {
            txt_daily.text = ss[num].toString()
        }

        val handler = @SuppressLint("HandlerLeak")
        object : Handler() {
            override fun handleMessage(msg: Message) {
                super.handleMessage(msg)
                when (msg.what) {
                    CHANGE_TEXT ->
                        //在这里可以进行更新UI操作
                        for (i in 0 until ss.size) {
                            if (ss[i].toString() == txt_daily.text) {
                                txt_daily.text = ss[i+1].toString()
                                break
                            }
                        }
                    else -> {
                    }
                }
            }
        }

        val calendar = Calendar.getInstance()
        calendar.set(Calendar.HOUR_OF_DAY, 0) // 控制时
        calendar.set(Calendar.MINUTE, 0) // 控制分
        calendar.set(Calendar.SECOND, 0) // 控制秒
        val timing = calendar.time     // 得出执行任务的时间,此处为今天的00：00：00
        val timer = Timer()
        timer.scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                //启动一个子线程
                Thread(Runnable {
                    //新建一个Message对象，存储需要发送的消息
                    val message = Message()
                    message.what = CHANGE_TEXT
                    //然后将消息发送出去
                    handler.sendMessage(message)
                }).start()
            }
        }, timing, 1000 * 60 * 60 * 24)// 这里设定将延时每天固定执行

//        if (time == tagTime){
//            for (i in 0 until ss.size) {
//                if (ss[i] == txt_daily.text) {
//                    txt_daily.text = ss[i+1].toString()
//                }
//            }
//        }
        txt_daily.onClick {
            for (i in 0 until ss.size) {
                if (ss[i].toString() == txt_daily.text) {
                    txt_daily.text = ss[i+1].toString()
                    num = i+1
                    break
                }
            }
        }
    }

//    override fun onSaveInstanceState(outState: Bundle?) {
//        outState!!.putString("key1",txt_daily.text.toString())
//        super.onSaveInstanceState(outState)
//    }
//
//    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
//        super.onRestoreInstanceState(savedInstanceState)
//    }

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


