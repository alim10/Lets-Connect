package org.alimapps.letsconnect.core.ui.components

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.PorterDuff
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.content.res.AppCompatResources
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.google.android.material.button.MaterialButton
import org.alimapps.letsconnect.core.ui.R
import org.alimapps.letsconnect.core.ui.databinding.LayoutProgressButtonBinding
import org.alimapps.letsconnect.core.ui.extention.colorStateListOf

class ProgressButton : ConstraintLayout {

    // Declare those two so we can destroy it into onDestroyView to prevent memory leaks
    private val binding get() = _binding!!
    private var _binding: LayoutProgressButtonBinding? = null

    private var mAttributes: AttributeSet? = null

    private var mButton:MaterialButton? = null
    private var mProgressBar:ProgressBar? = null
    private var loading: Boolean = false
    private var buttonEnabled:Boolean = true

    private var isLightTheme = false
    private var isChecked = true

    private var backgroundEnabledColor = resources.getColor(R.color.primary_color)
    private var backgroundDisabledColor = resources.getColor(R.color.transparent_color)
    private var contentEnabledColor = resources.getColor(R.color._white)
    private var contentDisabledColor = resources.getColor(R.color._grey)

    private var backgroundEnabledColorPrimary = resources.getColor(R.color.primary_color)
    private var backgroundDisabledColorPrimary = resources.getColor(R.color.transparent_color)
    private var contentEnabledColorPrimary = resources.getColor(R.color._white)
    private var contentDisabledColorPrimary = resources.getColor(R.color._grey)

    private var backgroundEnabledColorSecondary = resources.getColor(R.color.primary_color)
    private var backgroundDisabledColorSecondary = resources.getColor(R.color.transparent_color)
    private var contentEnabledColorSecondary = resources.getColor(R.color._white)
    private var contentDisabledColorSecondary = resources.getColor(R.color._grey)

    private var radius = resources.getDimension(R.dimen.button_corner_radius)
    private var icon: Drawable? = null
    private var customIconGravity: Int = MaterialButton.ICON_GRAVITY_START
    private var text: String? = null
    private var textSize = resources.getDimension(R.dimen.default_textsize)
    private var textStyle = 0
    private var strokeColor: Int = 0
    private var strokeWidth: Int = 0


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

    private fun init(attributes: AttributeSet?) {
        val attrs = attributes ?: return
        mAttributes = attrs
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.ProgressButton)
        isLightTheme = typedArray.getBoolean(R.styleable.ProgressButton_lightTheme, false)
        isChecked = typedArray.getBoolean(R.styleable.ProgressButton_isChecked, true)
        if(isLightTheme){
            backgroundEnabledColorPrimary = typedArray.getColor(R.styleable.ProgressButton_backgroundEnabledColor, resources.getColor(R.color._white))
            backgroundDisabledColorPrimary = typedArray.getColor(R.styleable.ProgressButton_backgroundDisabledColor, resources.getColor(R.color.transparent_color))
            contentEnabledColorPrimary = typedArray.getColor(R.styleable.ProgressButton_android_textColor, resources.getColor(R.color.primary_color))
            contentDisabledColorPrimary = typedArray.getColor(R.styleable.ProgressButton_contentDisabledColor, resources.getColor(R.color._grey))

            backgroundEnabledColorSecondary = typedArray.getColor(R.styleable.ProgressButton_backgroundEnabledColor, resources.getColor(R.color.primary_color))
            backgroundDisabledColorSecondary = typedArray.getColor(R.styleable.ProgressButton_backgroundDisabledColor, resources.getColor(R.color.transparent_color))
            contentEnabledColorSecondary= typedArray.getColor(R.styleable.ProgressButton_android_textColor, resources.getColor(R.color._white))
            contentDisabledColorSecondary = typedArray.getColor(R.styleable.ProgressButton_contentDisabledColor, resources.getColor(R.color._grey))
        }else{
            backgroundEnabledColorPrimary = typedArray.getColor(R.styleable.ProgressButton_backgroundEnabledColor, resources.getColor(R.color.primary_color))
            backgroundDisabledColorPrimary = typedArray.getColor(R.styleable.ProgressButton_backgroundDisabledColor, resources.getColor(R.color.transparent_color))
            contentEnabledColorPrimary = typedArray.getColor(R.styleable.ProgressButton_android_textColor, resources.getColor(R.color._white))
            contentDisabledColorPrimary = typedArray.getColor(R.styleable.ProgressButton_contentDisabledColor, resources.getColor(R.color._grey))

            backgroundEnabledColorSecondary = typedArray.getColor(R.styleable.ProgressButton_backgroundEnabledColor, resources.getColor(R.color._white))
            backgroundDisabledColorSecondary = typedArray.getColor(R.styleable.ProgressButton_backgroundDisabledColor, resources.getColor(R.color.transparent_color))
            contentEnabledColorSecondary = typedArray.getColor(R.styleable.ProgressButton_android_textColor, resources.getColor(R.color.primary_color))
            contentDisabledColorSecondary = typedArray.getColor(R.styleable.ProgressButton_contentDisabledColor, resources.getColor(R.color._grey))
        }

        radius = typedArray.getDimension(R.styleable.ProgressButton_buttonCornerRadius, resources.getDimension(R.dimen.button_corner_radius))
        buttonEnabled = typedArray.getBoolean(R.styleable.ProgressButton_android_enabled, true)
        text = typedArray.getString(R.styleable.ProgressButton_android_text)
        textSize = typedArray.getDimension(R.styleable.ProgressButton_android_textSize, resources.getDimension(R.dimen.default_textsize))
        textStyle = typedArray.getInt(R.styleable.ProgressButton_customTextStyle, 0)
        val iconResId = typedArray.getResourceId(R.styleable.ProgressButton_android_icon, -1)
        customIconGravity = typedArray.getInt(R.styleable.ProgressButton_customIconGravity, 1)
        strokeColor = typedArray.getColor(R.styleable.ProgressButton_pb_strokeColor, 0)
        strokeWidth = typedArray.getDimensionPixelSize(R.styleable.ProgressButton_strokeWidth, 0)

        typedArray.recycle()

        if(iconResId != -1){
            icon = AppCompatResources.getDrawable(context, iconResId)
        }

        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        //_binding = LayoutProgressButtonBinding.inflate(inflater)
        _binding = LayoutProgressButtonBinding.inflate(LayoutInflater.from(context), this, true)

        mButton = binding.button
        mProgressBar = binding.progressBar

        bind()
    }

    private fun bind(){
        if(isChecked){
            backgroundEnabledColor = backgroundEnabledColorPrimary
            backgroundDisabledColor = backgroundDisabledColorPrimary
            contentEnabledColor = contentEnabledColorPrimary
            contentDisabledColor = contentDisabledColorPrimary
        }else{
            backgroundEnabledColor = backgroundEnabledColorSecondary
            backgroundDisabledColor = backgroundDisabledColorSecondary
            contentEnabledColor = contentEnabledColorSecondary
            contentDisabledColor = contentDisabledColorSecondary
        }

        mButton?.setCornerRadius(radius.toInt())
        mButton?.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize)
        mButton?.strokeColor = ColorStateList.valueOf(strokeColor)
        mButton?.strokeWidth = strokeWidth

        when(textStyle){
            0 -> mButton?.setTypeface(mButton?.typeface, Typeface.NORMAL)
            1 -> mButton?.setTypeface(mButton?.typeface, Typeface.BOLD)
            2 -> mButton?.setTypeface(mButton?.typeface, Typeface.ITALIC)
        }
        mProgressBar?.indeterminateDrawable?.setColorFilter(contentEnabledColor, PorterDuff.Mode.MULTIPLY)

        if(buttonEnabled){
            mButton?.setBackgroundColor(backgroundEnabledColor)
        }else{
            mButton?.setBackgroundColor(backgroundDisabledColor)
        }

        mButton?.isEnabled = buttonEnabled

        if(loading){
            //show progressBar and hide MaterialButton
            mProgressBar?.visibility = VISIBLE
            //mButton?.visibility = View.INVISIBLE

            /*mButton?.text = ""
            mButton?.icon = null*/
            mButton?.setTextColor(backgroundEnabledColor)
            val colorList = colorStateListOf(
                intArrayOf(android.R.attr.state_enabled) to backgroundEnabledColor,
                intArrayOf(android.R.attr.state_enabled) to backgroundEnabledColor
            )
            mButton?.iconTint = colorList

            mButton?.isClickable = false
        }else{
            //show MaterialButton and hide progressBar
            mProgressBar?.visibility = INVISIBLE
            //mButton?.visibility = View.VISIBLE
            mButton?.text = text
            if(icon != null) {
                if(isChecked){
                    mButton?.icon = icon
                    mButton?.iconGravity = customIconGravity
                    //mButton?.iconPadding = -(icon?.intrinsicWidth ?: 0)

                }else{
                    mButton?.icon = null
                }
            }

            mButton?.isClickable = true

            if(buttonEnabled){
                mButton?.setTextColor(contentEnabledColor)
                val colorList = colorStateListOf(
                    intArrayOf(android.R.attr.state_enabled) to contentEnabledColor,
                    intArrayOf(android.R.attr.state_enabled) to contentEnabledColor
                )
                mButton?.iconTint = colorList
            }else{
                mButton?.setTextColor(contentDisabledColor)
                val colorList = colorStateListOf(
                    intArrayOf(android.R.attr.state_enabled) to contentDisabledColor,
                    intArrayOf(android.R.attr.state_enabled) to contentDisabledColor
                )
                mButton?.iconTint = colorList
            }
        }
    }

    override fun setEnabled(enabled: Boolean) {
        super.setEnabled(enabled)
        buttonEnabled = enabled
        bind()
    }

    fun setLoading(isLoading: Boolean){
        if(buttonEnabled){
            loading = isLoading
            bind()
        }
    }

    fun isLoading():Boolean{
        return loading
    }
    
    fun setTextColor(colorId: Int) {
        contentEnabledColorPrimary = ContextCompat.getColor(context, colorId)
        contentEnabledColorSecondary = ContextCompat.getColor(context, colorId)
        bind()
    }
    
    override fun setOnClickListener(l: OnClickListener?) {
        mButton?.setOnClickListener(l)
    }

    fun setText(text: String){
        this.text = text
        bind()
    }
}