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

    fun initialize(): FirebaseApp {
        FirebaseApp.getApps(context).firstOrNull() {
            it.name == FirebaseApp.DEFAULT_APP_NAME
        }?.let {
            return it
        }
        val option = parseFirebaseOptionFromAssets(context)
        return FirebaseApp.initializeApp(context, option)
    }

    private fun parseFirebaseOptionFromAssets(context: Context): FirebaseOptions {
        val json = context.assets.open("google-services.json")
            .bufferedReader()
            .use { it.readText() }
        val jsonObject = JSONObject(json)
        val projectInfo = jsonObject.getJSONObject("project_info")
        val client = jsonObject.getJSONArray("client").getJSONObject(0)

        val appId = client.getJSONObject("client_info").getString("mobilesdk_app_id")

        val gcmSenderId = projectInfo.getString("project_number")
        val projectId = projectInfo.getString("project_id")
//        val databaseUrl = projectInfo.getString("firebase_url")
        val storageBucket = projectInfo.getString("storage_bucket")

        return FirebaseOptions.Builder()
//            .setApiKey()
            .setApplicationId(appId)
            .setProjectId(projectId)
            .setStorageBucket(storageBucket)
            .setGcmSenderId(gcmSenderId)
            .build()

    }

}
