package org.alimapps.letsconnect.di.firebase

import android.content.Context
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import dagger.hilt.android.qualifiers.ApplicationContext
import org.json.JSONObject
import javax.inject.Inject

class FirebaseInitializer @Inject constructor(
    @ApplicationContext private val context: Context
) {

    fun initialize(): FirebaseApp? {
        FirebaseApp.getApps(context).firstOrNull {
            it.name == FirebaseApp.DEFAULT_APP_NAME
        }?.let {
            return it
        }
        return try {
            val option = parseFirebaseOptionFromAssets(context)
            FirebaseApp.initializeApp(context, option)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    private fun parseFirebaseOptionFromAssets(context: Context): FirebaseOptions {
        val json = context.assets.open("google-services.json")
            .bufferedReader()
            .use { it.readText() }
        val jsonObject = JSONObject(json)
        val projectInfo = jsonObject.getJSONObject("project_info")
        val clients = jsonObject.getJSONArray("client")

        val packageName = context.packageName
        var client: JSONObject? = null
        for (i in 0 until clients.length()) {
            val c = clients.getJSONObject(i)
            val clientPackageName = c.getJSONObject("client_info")
                .getJSONObject("android_client_info")
                .getString("package_name")
            if (clientPackageName == packageName) {
                client = c
                break
            }
        }

        if (client == null) {
            client = clients.getJSONObject(0)
        }

        val clientInfo = client.getJSONObject("client_info")
        val appId = clientInfo.getString("mobilesdk_app_id")
        val apiKey = client.getJSONArray("api_key").getJSONObject(0).getString("current_key")

        val gcmSenderId = projectInfo.getString("project_number")
        val projectId = projectInfo.getString("project_id")
        val storageBucket = projectInfo.optString("storage_bucket")

        return FirebaseOptions.Builder()
            .setApiKey(apiKey)
            .setApplicationId(appId)
            .setProjectId(projectId)
            .setStorageBucket(storageBucket)
            .setGcmSenderId(gcmSenderId)
            .build()
    }

}
