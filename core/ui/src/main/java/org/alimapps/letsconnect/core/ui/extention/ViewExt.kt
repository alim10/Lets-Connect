package org.alimapps.letsconnect.core.ui.extention

import android.app.Activity
import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.Animatable
import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Bundle
import android.os.SystemClock
import android.text.Editable
import android.text.TextWatcher
import android.util.DisplayMetrics
import android.util.TypedValue
import android.view.*
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.TextView
import androidx.annotation.AttrRes
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.annotation.DimenRes
import androidx.annotation.StringRes
import androidx.appcompat.R
import androidx.core.content.ContextCompat
import androidx.core.content.res.getDrawableOrThrow
import androidx.core.graphics.ColorUtils
import androidx.core.graphics.drawable.toBitmap
import androidx.core.view.isVisible
import androidx.core.view.updateLayoutParams
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.color.MaterialColors
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow

private const val HALF_ROTATION = 180F
private const val ZERO_ROTATION = 0F

/**
 * Show the view  (visibility = View.VISIBLE)
 */
fun View.visible(): View {
    if (visibility != View.VISIBLE) {
        visibility = View.VISIBLE
    }
    return this
}

/**
 * Hide the view. (visibility = View.INVISIBLE)
 */
fun View.hide(): View {
    if (visibility != View.INVISIBLE) {
        visibility = View.INVISIBLE
    }
    return this
}

/**
 * Remove the view (visibility = View.GONE)
 */
fun View.gone(): View {
    if (visibility != View.GONE) {
        visibility = View.GONE
    }
    return this
}

fun View.showView(show: Boolean) = if (show) visible() else gone()
fun View.showViewInvisible(show: Boolean) = if (show) visible() else hide()

/**
 * Try to hide the keyboard and returns whether it worked
 * https://stackoverflow.com/questions/1109022/close-hide-the-android-soft-keyboard
 */
fun View.hideKeyboard(): Boolean {
    try {
        val inputMethodManager =
            context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        return inputMethodManager.hideSoftInputFromWindow(windowToken, 0)
    } catch (ignored: RuntimeException) {
    }
    return false
}

/**
 * Enable the view (isEnabled = true)
 **/
fun View.enable(): View {
    isEnabled = true
    return this
}
fun View.enable(isEnabled:Boolean): View {
    if (isEnabled)
        enable()
    else disable()
    return this
}

/**
 *  Enable the view (isEnabled = false)
 **/
fun View.disable(): View {
    isEnabled = false
    return this
}


/**
 * Clickable
 * Sets view to be clickable or not
 * @param isClickable by default is true
 * @return
 */
fun View.clickable(isClickable: Boolean = true): View {
    this.isClickable = isClickable
    return this
}

/**
 * Fade view in/out upon passed boolean.
 **/
fun View.fade(state: Boolean) {
    if (this.isVisible && this.isShown && state) return
    isVisible = state
    if (state) this.alpha = 0.0f
    // Animate
    animate().alpha(if (state) 1.0f else 0.0f).duration = 300
}

/**
 *  [View] Fade in animation using built-in [AlphaAnimation]
 **/
fun View.alphaFadeIn() {
    val anim = AlphaAnimation(0.0f, 1.0f).apply {
        duration = 350
        setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation?) {
                visibility = View.VISIBLE
            }
            
            override fun onAnimationEnd(animation: Animation?) = Unit
            
            override fun onAnimationRepeat(animation: Animation?) = Unit
            
        })
    }
    this.startAnimation(anim)
}

/**
 * Add ripple effect to the [View]
 */
fun View.addRipple() = with(TypedValue()) {
    context.theme.resolveAttribute(R.attr.selectableItemBackground, this, true)
    setBackgroundResource(resourceId)
}

/**
 * Prevent multiple clicks by adding debounce between each click
 */
//fun View.setOnSingleClickListener(l: View.OnClickListener) {
//    setOnClickListener(OnSingleClickListener(l))
//}


/**
 * Set onTextChange to [EditText] and return the text afterTextChanged
 */
fun EditText.onTextChange(text: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            text(s.toString())
        }
        
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
    })
}

fun EditText.textChanges(emitEmptyText: Boolean = false) = callbackFlow {
    val listener = object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            val text = s.toString().trim()
            if (emitEmptyText) trySend(text) else if (text.isNotEmpty()) trySend(text)
        }
        
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
    }
    addTextChangedListener(listener)
    awaitClose { removeTextChangedListener(listener) }
}

fun View.dpi(dp: Int) = dpf(dp.toFloat()).toInt()

fun View.dpf(dp: Float): Float {
    return TypedValue
        .applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            dp,
            context.resources.displayMetrics
        )
}

fun colorStateListOf(vararg mapping: Pair<IntArray, Int>): ColorStateList {
    val (states, colors) = mapping.unzip()
    return ColorStateList(states.toTypedArray(), colors.toIntArray())
}

fun colorStateListOf(@ColorInt color: Int): ColorStateList {
    return ColorStateList.valueOf(color)
}

//fun attrColorStateListOf(context: Context, @AttrRes color: Int): ColorStateList {
//    return MaterialColors.getColorStateList(context, color,colorStateListOf(R.attr.primaryColor))
//}

fun colorStateListOf(colorHex: String): ColorStateList {
    return ColorStateList.valueOf(Color.parseColor(colorHex))
}

fun ViewBinding.colorStateListOf(@ColorRes colorId: Int): ColorStateList {
    return ColorStateList.valueOf(ContextCompat.getColor(context, colorId))
}

//fun Context.getColorStateListFromAttr(@AttrRes attr: Int): ColorStateList {
//    return colorStateListOf(getColorFromTheme(attr))
//}

/**
 * Show loading indicator within [TextInputLayout]
 */
//fun TextInputLayout.showLoading(
//    showLoading: Boolean,
//    drawableResourceId: Int = R.drawable.ic_search
//) {
//    if (showLoading) {
//        endIconDrawable = context.getProgressBarDrawable()
//        (endIconDrawable as? Animatable)?.start()
//    } else {
//        endIconDrawable = ContextCompat.getDrawable(context, drawableResourceId)
//    }
//}

fun Context.getProgressBarDrawable(): Drawable {
    val value = TypedValue()
    theme.resolveAttribute(android.R.attr.progressBarStyleSmall, value, false)
    val progressBarStyle = value.data
    val attributes = intArrayOf(android.R.attr.indeterminateDrawable)
    val array = obtainStyledAttributes(progressBarStyle, attributes)
    val drawable = array.getDrawableOrThrow(0)
    array.recycle()
    return drawable
}

fun String?.valueOrPlaceholder(): String {
    return when (this.isNullOrEmpty()) {
        true -> "--"
        else -> this
    }
}

fun Fragment.setFragmentResult(requestKey: String, bundle: Bundle) {
    parentFragmentManager.setFragmentResult(
        requestKey, bundle
    )
}

// Easier implementation of Fade out animation using built-in [AlphaAnimation]
fun View.alphaFadeOut() {
    val anim = AlphaAnimation(1.0f, 0.0f).apply {
        duration = 200
        setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation?) = Unit
            
            override fun onAnimationEnd(animation: Animation?) {
                visibility = View.GONE
            }
            
            override fun onAnimationRepeat(animation: Animation?) = Unit
            
        })
    }
    this.startAnimation(anim)
}

@Deprecated("user View.hideKeyboard() instead")
fun Activity.hideKeyboard() {
    val imm: InputMethodManager =
        getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    //Find the currently focused view, so we can grab the correct window token from it.
    var view = currentFocus
    //If no view currently has focus, create a new one, just so we can grab a window token from it
    if (view == null) {
        view = View(this)
    }
    imm.hideSoftInputFromWindow(view.windowToken, 0)
} // TODO: 04/09/2021 @Ahmed Ibrahim to be delete after Dependent new phase

@Deprecated("user View.showKeyboard() instead")
fun Activity.showKeyboard() {
    val imm: InputMethodManager =
        getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    //Find the currently focused view, so we can grab the correct window token from it.
    val view = currentFocus
    imm.toggleSoftInputFromWindow(view?.windowToken, InputMethodManager.SHOW_FORCED, 0)
} // TODO: 04/09/2021 @Ahmed Ibrahim to be delete after Dependent new phase

/**
 * Show [Snackbar] with passed
 *
 * @param messageResId id for message
 * @param length [Snackbar.LENGTH]
 * @param actionTitleResId id for action button text
 * @param onActionClick action to be triggered once attached to [Snackbar]
 *
 */
fun View.showSnackBar(
    @StringRes messageResId: Int,
    length: Int = Snackbar.LENGTH_SHORT,
    @StringRes actionTitleResId: Int? = null,
    onActionClick: (() -> Unit)? = null,
) {
    val snackbar = Snackbar.make(
        this,
        messageResId,
        length
    )
    actionTitleResId?.let {
        snackbar.setAction(it) {
            snackbar.dismiss()
            onActionClick?.invoke()
        }
    }
    snackbar.show()
}

/**
 * set [BottomSheetBehavior] to [BottomSheetBehavior.STATE_HIDDEN]
 */
fun BottomSheetBehavior<out View>.hide() {
    this.state = BottomSheetBehavior.STATE_HIDDEN
}

// endregion

fun RecyclerView.resetToTopWhenUpdated() {
    this.adapter?.registerAdapterDataObserver(object : RecyclerView.AdapterDataObserver() {
        override fun onChanged() {
            this@resetToTopWhenUpdated.scrollToPosition(0)
        }
        
        override fun onItemRangeRemoved(positionStart: Int, itemCount: Int) {
            this@resetToTopWhenUpdated.scrollToPosition(0)
        }
        
        override fun onItemRangeMoved(fromPosition: Int, toPosition: Int, itemCount: Int) {
            this@resetToTopWhenUpdated.scrollToPosition(0)
        }
        
        override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
            this@resetToTopWhenUpdated.scrollToPosition(0)
        }
        
        override fun onItemRangeChanged(positionStart: Int, itemCount: Int) {
            this@resetToTopWhenUpdated.scrollToPosition(0)
        }
        
        override fun onItemRangeChanged(positionStart: Int, itemCount: Int, payload: Any?) {
            this@resetToTopWhenUpdated.scrollToPosition(0)
        }
    })
}

fun Fragment.hideKeyboard() = view?.hideKeyboard()

/**
 * Listen to [ViewTreeObserver.OnGlobalLayoutListener] and execute action,
 *
 * with safely cancel task to prevent any leaks.
// */
//fun View.onGlobalLayout(
//    block: () -> Unit
//): CancelableTask {
//    val listener = ViewTreeObserver.OnGlobalLayoutListener { block() }
//    viewTreeObserver.addOnGlobalLayoutListener(listener)
//
//    return SafeCancelTask {
//        viewTreeObserver.removeOnGlobalLayoutListener(listener)
//    }
//}

fun View.onClick(interval: Int = 1000, body: (View) -> Unit) =
    setOnClickListener(SingleClickListener(interval) { body(it) })

/**
 * This type of [View.OnClickListener] prevents multiple view clicks at one time.
 */
class SingleClickListener(
    private var defaultInterval: Int = 1000,
    private val onSingleClick: (View) -> Unit,
) : View.OnClickListener {
    private var lastTimeClicked: Long = 0
    override fun onClick(v: View) {
        if (SystemClock.elapsedRealtime() - lastTimeClicked < defaultInterval) {
            return
        }
        lastTimeClicked = SystemClock.elapsedRealtime()
        onSingleClick(v)
    }
}

fun TextView.setCopyPasteEnabled(isEnabled: Boolean = false) {
    isLongClickable = isEnabled
    isFocusableInTouchMode = isEnabled
    setTextIsSelectable(isEnabled)
    customSelectionActionModeCallback = object : ActionMode.Callback {
        override fun onCreateActionMode(mode: ActionMode?, menu: Menu): Boolean = isEnabled
        override fun onPrepareActionMode(mode: ActionMode?, menu: Menu): Boolean = isEnabled
        override fun onActionItemClicked(mode: ActionMode?, item: MenuItem): Boolean = isEnabled
        override fun onDestroyActionMode(mode: ActionMode?) = Unit
    }
}

fun NavigationView.setDrawerMargin(
    activity: Activity?, @DimenRes drawerMarginRes: Int
) = runCatching {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
        val windowMetrics = activity?.windowManager?.maximumWindowMetrics
        val widthPixels: Int = windowMetrics?.bounds?.width() ?: 0
        updateLayoutParams {
            width = widthPixels - resources.getDimensionPixelOffset(drawerMarginRes)
        }
    } else {
        val metrics = DisplayMetrics()
        activity?.windowManager?.defaultDisplay?.getMetrics(metrics)
        updateLayoutParams {
            width = metrics.widthPixels - resources.getDimensionPixelOffset(drawerMarginRes)
        }
    }
}.getOrNull()

//fun Drawable.getDominantColor(context: Context?, block: (dominantColor: Int) -> Unit) {
//    val bitmap = this.toBitmap()
//    Palette.Builder(bitmap).generate {
//        it?.let { palette ->
//            context?.let { context ->
//                val dominantColor = palette.getDominantColor(
//                    ContextCompat.getColor(context, R.color._black)
//                )
//                block(dominantColor)
//            }
//        }
//    }
//}

fun isDarkColor(@ColorInt color: Int): Boolean {
    return ColorUtils.calculateLuminance(color) < 0.5
}

inline var ViewBinding.isVisible: Boolean
    get() = root.visibility == View.VISIBLE
    set(value) {
        root.isVisible = value
    }

//inline var ViewBinding.isShimmering: Boolean
//    get() = isVisible
//    set(value) {
//        isVisible = value == true
//        val shimmerView = root as? ShimmerFrameLayout
//        if (value) {
//            shimmerView?.startShimmer()
//        } else {
//            shimmerView?.stopShimmer()
//        }
//    }

val ViewBinding.context: Context get() = root.context

fun ViewBinding.gone() = apply {
    root.gone()
}

fun ViewBinding.hide() = apply {
    root.hide()
}

fun ViewBinding.show() = apply {
    root.visible()
}

val ViewGroup.layoutInflater: LayoutInflater get() = LayoutInflater.from(this.context)

fun View.animatedRotation(angle: Float, duration: Long = 200) = animate().setDuration(duration).rotation(angle)
fun View.animatedHalfRotation(rotate: Boolean, duration: Long = 200) =
    animatedRotation(if (rotate) HALF_ROTATION else ZERO_ROTATION, duration)

fun View.setVisibility(show: Boolean) {
    if (show) visible() else gone()
}

val View.screenLocation get(): IntArray {
    val point = IntArray(2)
    getLocationOnScreen(point)
    return point
}

fun View.screenLocationSafe(callback: (Int, Int) -> Unit) {
    post {
        val (x, y) = screenLocation
        callback(x, y)
    }
}
