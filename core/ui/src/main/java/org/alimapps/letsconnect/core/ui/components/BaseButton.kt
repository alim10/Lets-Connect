package org.alimapps.letsconnect.core.ui.components

import android.content.Context
import android.util.AttributeSet
import androidx.core.content.ContextCompat
import com.google.android.material.button.MaterialButton
import org.alimapps.letsconnect.core.ui.R

class BaseButton : MaterialButton {
    constructor(context: Context) : super(context)
    constructor(context: Context, attr: AttributeSet) : super(context, attr) {
        init()
    }

    constructor(context: Context, attr: AttributeSet, defStyle: Int) : super(
        context,
        attr,
        defStyle
    ) {
        init()
    }


    private fun init() {
        isClickable = true
        setTextColor(ContextCompat.getColor(context, R.color._white))
        if (isEnabled)
            setBackgroundColor(ContextCompat.getColor(context, R.color.primary_color))
        else
            setBackgroundColor(ContextCompat.getColor(context, R.color.secondary_title))
        isAllCaps = false
        background = ContextCompat.getDrawable(context, R.drawable.btn_bg)
    }
}