package com.alim.letsconnect.core.helper

import android.content.Context
import android.content.ContextWrapper
import android.content.res.Configuration
import android.os.Build
import java.util.*

class LanguageHelper(base: Context?) : ContextWrapper(base) {

    companion object {
        fun wrapper(context: Context, languageCode: String): ContextWrapper {
            var context = context
            val config = context.resources.configuration
            var sysLocale: Locale? = null
            sysLocale = if (Build.VERSION.SDK_INT > Build.VERSION_CODES.N) {
                getSystemLocale(config)
            } else {
                getSystemLocaleLegacy(
                    config
                )
            }
            if (languageCode != "" && sysLocale.language != languageCode) {
                val locale = Locale(languageCode)
                Locale.setDefault(locale)
                setSystemLocale(
                    config,
                    locale
                )
            }
            context = context.createConfigurationContext(config)
            return LanguageHelper(context)
        }

        private fun getSystemLocaleLegacy(config: Configuration): Locale {
            return config.locale
        }

        private fun getSystemLocale(config: Configuration): Locale {
            return config.locales[0]
        }

        private fun setSystemLocaleLegacy(config: Configuration, locale: Locale?) {
            config.locale = locale
        }

        private fun setSystemLocale(config: Configuration, locale: Locale?) {
            config.setLocale(locale)
        }
    }
}
