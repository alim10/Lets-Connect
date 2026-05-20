package org.alimapps.letsconnect.core.network.retrofit.interceptors


import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ChuckInterceptorInstance @Inject constructor(@ApplicationContext val context: Context) {
    val instance = ChuckerInterceptor.Builder(context).build()
}