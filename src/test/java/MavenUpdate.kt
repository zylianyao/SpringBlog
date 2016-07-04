import jodd.util.StringUtil
import org.pinae.nala.xb.Xml
import org.pinae.nala.xb.exception.MarshalException
import org.pinae.nala.xb.exception.UnmarshalException
import org.pinae.nala.xb.util.ResourceReader
import java.io.IOException
import java.util.*
import java.util.regex.Pattern

/**
 * 拿客
 * www.coderknock.com
 * QQ群：213732117
 * 创建时间：2016年07月04日
 * 描述：Maven更新
 */
fun main(args: Array<String>) {

    var p = Pattern.compile("\\$\\{([\\s\\S]*?)\\}")
    try {
        //获取到POM.xml
        val xml = ResourceReader().readFile("pom.xml")
        //Parse XML to Object
        val map = Xml.toMap(xml.toString(), "utf8")
        val versions = HashMap<String, String>()
        //获取POM.xml的dependencies节点
        val dependencies = map["dependencies"] as Map<*, *>
        //获取所有dependency并转换成一个  ArrayList<HashMap<String, String>>
        val dependencys = dependencies["dependency"] as ArrayList<HashMap<String, String>>
        dependencys.forEach { x ->
            //获取每个dependency的版本，此处版本使用${}属性的方式声明
            val m = p.matcher(x["version"])
            while (m.find()) {
                //查询最新版本号
                val version = Maven.getLatestVersion(x["groupId"], x["artifactId"])
                versions.put(m.group(1), version)
            }
        }
        //获取POM.xml中properties节点
        val properties = map["properties"] as HashMap<String, String>
        //循环比较版本的不同并替换
        properties.forEach { entry ->
            if (!StringUtil.isBlank(versions.get(entry.key)))
                properties.put(entry.key, versions.get(entry.key)!!)
        }
        //输出最新版本的  properties
        println(Xml.toXML(properties, "UTF-8", true))
    } catch (e: IOException) {
        e.printStackTrace()
    } catch (e: UnmarshalException) {
        e.printStackTrace()
    } catch (e: MarshalException) {
        e.printStackTrace()
    }
}
