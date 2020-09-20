package me.xiaox.molexhitokoto

import com.google.auto.service.AutoService
import me.xiaox.molexhitokoto.event.GroupMessage
import net.mamoe.mirai.console.plugin.jvm.JvmPlugin
import net.mamoe.mirai.console.plugin.jvm.JvmPluginDescription
import net.mamoe.mirai.console.plugin.jvm.KotlinPlugin
import net.mamoe.mirai.utils.info

@AutoService(JvmPlugin::class)
object MolexHitokoto : KotlinPlugin(
    JvmPluginDescription("me.xiaox.molex-hitokoto", "1.0-SNAPSHOT")
) {
    override fun onEnable() {
        GroupMessage.onGroupMessage()
        logger.info { "插件加载成功!" }
    }
}