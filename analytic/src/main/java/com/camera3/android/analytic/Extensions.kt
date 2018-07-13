package com.camera3.android.analytic

import android.os.Bundle
import org.json.JSONObject
import java.util.HashMap

fun Bundle.toMapAny(): HashMap<String, Any> {
    val map = HashMap<String, Any>()
    this.keySet().forEach { key ->
        val param = this.get(key)
        map[key] = param.toString()
    }
    return map
}

fun Bundle.toJson(): JSONObject {
    val obj = JSONObject()
    this.keySet().forEach { key ->
        val param = this.get(key)
        obj.put(key, param)
    }
    return obj
}