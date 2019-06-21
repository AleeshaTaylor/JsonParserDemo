package exam.aleeshataylor.jsonparserdemo.util

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import exam.aleeshataylor.jsonparserdemo.entity.School
import exam.aleeshataylor.jsonparserdemo.entity.Sentence
import org.json.JSONArray
import org.json.JSONObject

class ParserJson {
    /**
     * 解析JSON数组字符串
     * @param jsonArr JSON格式的字符串数组
     * @return  对象集合
     */
    @Throws(Exception::class)
    fun parserArrayForJSON(jsonArr: String): List<School> {
        val schools = ArrayList<School>()
        //将JSON字符串转成了JSON数组
        val array = JSONArray(jsonArr)
        //遍历JSON数组，数组的每一个元素都是一个JSONObject
        for (i in 0 until array.length()) {
            val obj = array.getJSONObject(i)
            val s = School(
                obj.getString("name"),
                obj.getString("province")
            )
            schools.add(s)
        }
        return schools
    }

    /**
     * 解析每日一句的JSON字符串
     */
    fun parserArrayForSentence(jsonArr: String): List<Sentence> {
        val sentences = ArrayList<Sentence>()
        val array = JSONArray(jsonArr)
        for (i in 0 until array.length()) {
            val obj: JSONObject = array.getJSONObject(i)
            val s = Sentence(
                obj.getString("hitokoto"),
                obj.getString("author")
            )
            sentences.add(s)
        }
        return sentences
    }

    /**
     * 使用GSON解析JSON数组
     * @param jsonArr JSON对象数组字符串
     * @return 对应的对象集合
     */
    fun parserArrayForGSON(jsonArr: String): List<School>{
        val gson = Gson()
        //利用反射机制，得到List<Person>的类型
        val type = object : TypeToken<List<School>>() {}.type
        return gson.fromJson(jsonArr, type)
    }

    fun parserArrayToSentence(jsonArr: String): List<Sentence>{
        val gson = Gson()
        //利用反射机制，得到List<Sentence>的类型
        val type = object : TypeToken<List<Sentence>>() {}.type
        return gson.fromJson(jsonArr, type)
    }
}