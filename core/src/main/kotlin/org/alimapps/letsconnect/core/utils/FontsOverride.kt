package org.alimapps.letsconnect.core.utils

import android.content.Context
import android.graphics.Typeface
import org.alimapps.letsconnect.core.logging.AppLogs.handleException
import java.lang.reflect.Field

object FontsOverride {

    fun setDefaultFont(
        context: Context,
        staticTypefaceFieldName: String,
        fontAssetName: String?
    ) {
        val regular = Typeface.createFromAsset(
            context.assets,
            fontAssetName
        )
        replaceFont(staticTypefaceFieldName, regular)
    }

    private fun replaceFont(
        staticTypefaceFieldName: String,
        newTypeface: Typeface
    ) {
        try {
            val staticField: Field = Typeface::class.java
                .getDeclaredField(staticTypefaceFieldName)
            staticField.isAccessible = true
            staticField.set(null, newTypeface)
        } catch (e: NoSuchFieldException) {
            handleException("FontsOverride", e)
        } catch (e: IllegalAccessException) {
            handleException("FontsOverride", e)
        }
    }
}