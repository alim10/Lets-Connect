package com.alim.letsconnect.core.utils

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

/**
 * High order functions to cut the boilerplate code of converting entities to be inserted into Room DB
 *
 * @author Ahmed Ibrahim
 */
fun <T> fromList(value: List<T>): String {
    val gson = Gson()
    val type = object : TypeToken<List<T>>() {}.type
    return gson.toJson(value, type)
}

inline fun <reified T> toList(value: String): List<T> {
    val gson = Gson()
    val type = object : TypeToken<List<T>>() {}.type
    return gson.fromJson(value, type)
}

fun <T> fromModel(value: T): String {
    val gson = Gson()
    val type = object : TypeToken<T>() {}.type
    return gson.toJson(value, type)
}

// using reified here to enforce compiler to define T : type unless GSON will throw error.
inline fun <reified T> toModel(json: String): T {
    val gson = Gson()
    return gson.fromJson(json, object : TypeToken<T>() {}.type)
}
