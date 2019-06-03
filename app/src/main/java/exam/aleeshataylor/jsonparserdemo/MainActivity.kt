package exam.aleeshataylor.jsonparserdemo

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import exam.aleeshataylor.jsonparserdemo.entity.School
import exam.aleeshataylor.jsonparserdemo.util.GetJsonUtil
import exam.aleeshataylor.jsonparserdemo.util.ParserJson
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.toast


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setListeners()
    }

    @SuppressLint("SetTextI18n")
    private fun setListeners() {
        val jsonArr = GetJsonUtil.getJson("school.json",this)
        val parser = ParserJson()
        //使用JSON解析
        btn_json.onClick {
            val ps = parser.parserArrayForJSON(jsonArr)
            toast("开始JSON解析！")
            txt_show.text = "JSON解析对象\n$ps"
        }
        //使用GSON解析
        btn_gson.onClick {
            val ps = parser.parserArrayForGSON(jsonArr)
            showJSONArrayData("GSON解析JSON数组", ps)
        }
    }

    /**
     * 显示JSON数组的值
     * @param type 当前用什么类型解析
     * @param ps 要显示数据的对象数组
     */
    @SuppressLint("SetTextI18n")
    fun showJSONArrayData(type: String, ps: List<School>) {
        txt_show.text = type + "\n"
        for (p in ps) {
            txt_show.append(p.toString() + "\n")
        }
    }
}
