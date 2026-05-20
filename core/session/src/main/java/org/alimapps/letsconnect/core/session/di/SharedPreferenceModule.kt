package org.alimapps.letsconnect.core.database.di

import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

const val APP_PREF_NAME = "MY_ENCRYPTED_PREFERENCES"
@Suppress("DEPRECATION")
@InstallIn(SingletonComponent::class)
@Module
class SharedPreferenceModule {
    @Singleton
    @Provides
    fun provideShared(@ApplicationContext context: Context): SharedPreferences {
        val masterKeyAlias = MasterKey.Builder(context)
            .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
            .build()
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            try {
                EncryptedSharedPreferences.create(
                    context,
                    APP_PREF_NAME,
                    masterKeyAlias,
                    EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                    EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
                )
            } catch (e: Exception) {
                context.getSharedPreferences(APP_PREF_NAME, Context.MODE_PRIVATE)
            }
        } else context.getSharedPreferences(APP_PREF_NAME, Context.MODE_PRIVATE)
    }
}
