package org.alimapps.letsconnect.core.ui.components

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import androidx.core.content.ContextCompat
import com.google.android.material.textfield.TextInputEditText
import org.alimapps.letsconnect.core.ui.R

class BaseEditText : TextInputEditText {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init(attrs)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init(attrs)
    }

    private var hasIcon: Boolean = false
    private var rightIcon: Int = 0
    private var leftIcon: Int = 0
    private var textBackground: Drawable? = null
    private var edtTextAppearance: Int = -1

    private fun init(attrs: AttributeSet?) {
        val typeArray = context.obtainStyledAttributes(attrs, R.styleable.BaseEditTextStyle)
        hint = typeArray.getString(R.styleable.BaseEditTextStyle_android_hint)
        textBackground = typeArray.getDrawable(R.styleable.BaseEditTextStyle_android_background)
            ?: ContextCompat.getDrawable(context, R.drawable.edt_bg)
        background = textBackground
        edtTextAppearance =
            typeArray.getResourceId(R.styleable.BaseEditTextStyle_android_textAppearance, 0)
        setTextAppearance(context, edtTextAppearance)
        hasIcon = typeArray.getBoolean(R.styleable.BaseEditTextStyle_hasIcon, false)
        setTextColor(typeArray.getColor(R.styleable.BaseEditTextStyle_textColor, Color.BLACK))
        textSize = typeArray.getDimension(R.styleable.BaseEditTextStyle_textSize, 10f)
        if (hasIcon) {
            rightIcon = typeArray.getResourceId(R.styleable.BaseEditTextStyle_iconRight, 0)
            leftIcon = typeArray.getResourceId(R.styleable.BaseEditTextStyle_iconLeft, 0)
            setCompoundDrawablesWithIntrinsicBounds(leftIcon, 0, rightIcon, 0)
        }
        typeArray.recycle()
    }
}