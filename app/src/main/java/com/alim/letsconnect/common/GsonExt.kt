package com.alim.letsconnect.common

import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type


fun Any?.toGson(): String = Gson().toJson(this)

fun <T> String?.toObject(classOfT: Class<T>): T? = Gson().fromJson(this, classOfT)

fun <T> String?.toListOfObject(classOfT: Class<T>?): List<T>? {
    val typeOfT: Type = TypeToken.getParameterized(MutableList::class.java, classOfT).type
    return Gson().fromJson(this, typeOfT)
}