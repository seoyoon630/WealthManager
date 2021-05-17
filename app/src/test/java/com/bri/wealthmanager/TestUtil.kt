@file:Suppress("EXPERIMENTAL_API_USAGE")

package com.bri.wealthmanager

fun log() {
    val log = Exception().stackTrace[1]
    val methodName = if (log.className.contains(Regex("[가-힣]"))) {
        val start = log.className.indexOf("$") + 1
        log.className.substring(start, log.className.lastIndex - 1)
    } else log.methodName
    println(methodName.replace("_", " ") + " 성공")
}

fun log(message: Any) {
    println(message.toString())
}
