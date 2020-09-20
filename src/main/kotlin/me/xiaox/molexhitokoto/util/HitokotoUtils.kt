package me.xiaox.molexhitokoto.util

import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.URL
import java.net.URLConnection
import java.nio.charset.StandardCharsets

object HitokotoUtils {

    private val json = Json { ignoreUnknownKeys = true }

    fun hitokoto(parm: String = ""): String {
        val text = if (parm.isEmpty()) {
            getStringByWeb("https://v1.hitokoto.cn/")
        } else {
            getStringByWeb("https://v1.hitokoto.cn/?c=$parm")
        }
        val data = kotlin.runCatching {
            json.decodeFromString<HitokotoData>(text)
        }.getOrElse {
            return "『数据错误』 - 「星空」"
        }
        return "『${data.hitokoto}』 - 「${data.from}」"
    }

    private fun getStringByWeb(url: String): String {
        val webText = StringBuilder()
        try {
            val urlObject = URL(url)
            val uc: URLConnection = urlObject.openConnection()
            val br = BufferedReader(InputStreamReader(uc.getInputStream(), StandardCharsets.UTF_8))
            var inputLine: String?
            while (br.readLine().also { inputLine = it } != null) {
                webText.append(inputLine)
            }
            br.close()
        } catch (e: IOException) {
            return ""
        }
        return webText.toString()
    }
}

@Serializable
data class HitokotoData(
    val hitokoto: String,
    val from: String
)