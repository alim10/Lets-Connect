package org.alimapps.letsconnect.apps

import android.app.Application
import org.alimapps.letsconnect.di.firebase.FirebaseInitializerEntryPoint
import dagger.hilt.android.EntryPointAccessors
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()

        initFirebase()
    }

    private fun initFirebase() {
        val firebaseInitializer = EntryPointAccessors.fromApplication(
            context = this, FirebaseInitializerEntryPoint::class.java
        ).getFirebaseInitializer()

        firebaseInitializer.initialize()
    }
}