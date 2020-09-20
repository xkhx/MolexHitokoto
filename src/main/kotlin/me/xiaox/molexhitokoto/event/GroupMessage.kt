package me.xiaox.molexhitokoto.event

import kotlinx.coroutines.launch
import me.xiaox.molexhitokoto.MolexHitokoto
import me.xiaox.molexhitokoto.util.HitokotoUtils
import net.mamoe.mirai.event.subscribeGroupMessages

object GroupMessage {
    fun onGroupMessage() {
        MolexHitokoto.launch {
            subscribeGroupMessages {
                startsWith("/") {
                    val command = it.split(" ")
                    val root = command[0].toLowerCase()
                    val args = if (command.size > 1) command.subList(1, command.size) else listOf()

                    if (root == "一言") {
                        if (args.isEmpty()) {
                            reply(HitokotoUtils.hitokoto())
                            return@startsWith
                        }
                        if (args[0].toLowerCase() == "help") {
                            reply(
                                "一言 > 帮助列表\n" +
                                        "/一言 - 随机返回一条一言\n" +
                                        "/一言 [参数] - 返回指定的一言"
                            )
                        } else {
                            reply(HitokotoUtils.hitokoto(args[0].toLowerCase()))
                        }
                    }
                }
            }
        }
    }
}