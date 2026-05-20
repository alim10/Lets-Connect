package org.alimapps.letsconnect.core.common.encyrption

import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec
import android.util.Base64
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.alimapps.letsconnect.core.data.nativeLib.Secrets

fun encrypt(stringToEncrypt: String, key: String = Secrets.encryptionKey(), iv: String = Secrets.encryptionIVKey()): String? {
    try {
        val passwordBytes= stringToEncrypt.toByteArray(Charsets.UTF_8)
        val keyBytes = key.toByteArray(Charsets.UTF_8)
        val ivBytes = iv.toByteArray(Charsets.UTF_8)

        val secretKeySpec = SecretKeySpec(keyBytes, "AES")
        val ivParameterSpec = IvParameterSpec(ivBytes)
        val cipher = Cipher.getInstance("AES/CBC/PKCS7Padding")
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec)

        val encryptedBytes = cipher.doFinal(passwordBytes)
        return Base64.encodeToString(encryptedBytes, Base64.DEFAULT).trim()

    } catch (e: Exception) {
        println("Encryption error: ${e.message}")
        return null
    }
}

fun decrypt(stringToDecrypt: String, key: String = Secrets.encryptionKey(), iv: String = Secrets.encryptionIVKey()): String? {
    try {
        val encryptedBytes = Base64.decode(stringToDecrypt, Base64.DEFAULT)
        val keyBytes = key.toByteArray(Charsets.UTF_8)
        val ivBytes = iv.toByteArray(Charsets.UTF_8)

        val secretKeySpec = SecretKeySpec(keyBytes, "AES")
        val ivParameterSpec = IvParameterSpec(ivBytes)
        val cipher = Cipher.getInstance("AES/CBC/PKCS7Padding")
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec)

        val decryptedBytes = cipher.doFinal(encryptedBytes)
        return String(decryptedBytes, Charsets.UTF_8)

    } catch (e: Exception) {
        println("Decryption error: ${e.message}")
        return null
    }
}


inline fun <reified T> String.fromBase64Json(): T {
    val json = String(
        Base64.decode(this, Base64.DEFAULT),
        Charsets.UTF_8
    )
    return Gson().fromJson(json, object : TypeToken<T>() {}.type)
}
