package org.alimapps.letsconnect.core.common.utils

import android.app.Application
import android.content.res.Configuration
import android.os.Build
import android.view.ContextThemeWrapper
import java.util.Locale

object LocaleUtils {
    private var sLocale: Locale? = null

    @JvmStatic
    fun setLocale(locale: Locale) {
        sLocale = locale
        sLocale?.let { Locale.setDefault(it) }
    }

    @JvmStatic
    fun updateConfig(wrapper: ContextThemeWrapper) {
        sLocale?.let {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                val configuration = Configuration().apply {
                    setLocale(it)
                    setLayoutDirection(it)
                }
                wrapper.applyOverrideConfiguration(configuration)
            }
        }
    }

    @JvmStatic
    fun updateConfig(app: Application, configuration: Configuration) {
        sLocale?.let {
            configuration.setLocale(it)
            configuration.setLayoutDirection(it)
            app.resources.updateConfiguration(configuration, app.resources.displayMetrics)
        }
    }
}


