package org.alimapps.letsconnect.core.ui.base

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.annotation.ColorInt
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Density
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import org.alimapps.letsconnect.core.analytics.BuildConfig
import org.alimapps.letsconnect.core.common.extension.startLauncherActivity
import org.alimapps.letsconnect.core.common.general.ErrorCodes
import org.alimapps.letsconnect.core.common.general.ErrorObject
import org.alimapps.letsconnect.core.common.logging.error
import org.alimapps.letsconnect.core.common.utils.LocaleUtils
import org.alimapps.letsconnect.core.ui.R
import org.alimapps.letsconnect.core.ui.bottomsheet.ErrorBottomSheet
import org.alimapps.letsconnect.core.ui.dialogs.ProgressDialog
import org.alimapps.letsconnect.core.ui.extention.isDarkColor
import org.alimapps.letsconnect.core.ui.progress.IProgressDialogInteractor
import org.alimapps.letsconnect.core.ui.progress.ProgressDialogInteractorImpl
import java.lang.ref.WeakReference
import javax.inject.Inject

abstract class BaseActivity : AppCompatActivity(),
    IProgressDialogInteractor by ProgressDialogInteractorImpl() {

    @Inject
    lateinit var progressDialog: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        registerDialogInitActivity(WeakReference(this))
        val defaultColor = ContextCompat.getColor(this, R.color.primary_background)
        setStatusBarColor(defaultColor)
        setNavigationBarColor(defaultColor)
    }

    protected fun ComposeView.setContentThemed(content: @Composable () -> Unit) {
        setContent {
            val currentDensity = LocalDensity.current
            val customDensity = Density(
                density = currentDensity.density,
                fontScale = currentDensity.fontScale.coerceAtMost(MAX_UI_INCREASE_RATIO)
            )

            CompositionLocalProvider(LocalDensity provides customDensity) {
//                LeanTheme {
//                    content()
//                }
            }
        }
    }

    open fun setStatusBarColor(@ColorInt color: Int) {
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = color
        val windowInsetController = WindowCompat.getInsetsController(window, window.decorView)
        windowInsetController.isAppearanceLightStatusBars = isDarkColor(color).not()
    }

    open fun setNavigationBarColor(@ColorInt color: Int) {
        window.navigationBarColor = color
        val windowInsetController = WindowCompat.getInsetsController(window, window.decorView)
        windowInsetController.isAppearanceLightNavigationBars = isDarkColor(color).not()
        View.SYSTEM_UI_FLAG_VISIBLE
    }

    override fun attachBaseContext(newBase: Context) {
        val configuration = newBase.resources.configuration

        if (configuration.fontScale > MAX_UI_INCREASE_RATIO) {
            configuration.fontScale = MAX_UI_INCREASE_RATIO
        }
        val context = newBase.createConfigurationContext(configuration)
        LocaleUtils.updateConfig(this)
        super.attachBaseContext(context)
    }

    protected fun addActivityMargins(
        addTopMargin: Boolean = false,
        addBottomMargin: Boolean = true
    ) {
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(android.R.id.content)) { view, insets ->
            val params = view.layoutParams as ViewGroup.MarginLayoutParams
            if (addBottomMargin) params.bottomMargin = insets.systemWindowInsetBottom
            if (addTopMargin) params.topMargin = insets.systemWindowInsetTop
            insets //insets.consumeSystemWindowInsets()
        }
    }

    fun showLoading(isLoading: Boolean) =
        if (isLoading) showProgressDialog() else hideProgressDialog()

    protected fun showErrorPopUp(
        errorObject: ErrorObject?,
        positiveActionText: String = resources.getString(R.string.ok),
        negativeActionText: String = "",
        positiveAction: () -> Unit = {},
        negativeAction: () -> Unit = {}
    ) {
        var title = ""
        var message = ""
        when (errorObject?.code) {
            ErrorCodes.NETWORK_ERROR -> {
                title = resources.getString(R.string.error)
                message = resources.getString(R.string.error_occurred)
                showDialog(
                    title,
                    message,
                    positiveActionText,
                    negativeActionText,
                    positiveAction,
                    negativeAction
                )
            }

            ErrorCodes.CONNECTIVITY_ERROR -> {
                title = resources.getString(R.string.no_internet_title)
                message = resources.getString(R.string.no_internet_message)
                showDialog(
                    title,
                    message,
                    positiveActionText,
                    negativeActionText,
                    positiveAction,
                    negativeAction
                )
            }

            ErrorCodes.UNKNOWN_ERROR -> {
                title = resources.getString(R.string.error)
                message = resources.getString(R.string.error_occurred)
                showDialog(
                    title,
                    message,
                    positiveActionText,
                    negativeActionText,
                    positiveAction,
                    negativeAction
                )
            }

            ErrorCodes.UNAUTHENTICATED_ERROR -> {
                title = resources.getString(R.string.login_required)
                message = resources.getString(R.string.you_need_to_login_again)
                val loginAction = {
                    baseContext.startLauncherActivity()
                    finish()
                }
                showDialog(
                    title,
                    message,
                    positiveActionText,
                    negativeActionText,
                    loginAction,
                    negativeAction
                )
            }

            ErrorCodes.LOCALIZED_ERROR -> {
                if (errorObject.additionalInfo.isNullOrEmpty()) {
                    title = resources.getString(R.string.error)
                    message = errorObject.message.toString().ifEmpty { resources.getString(R.string.error_occurred) }
                } else {
                    title = if (errorObject.message.toString()
                            .isNotEmpty()
                    ) errorObject?.message.toString() else resources.getString(R.string.error)
                    message = errorObject.additionalInfo.toString().ifEmpty { resources.getString(R.string.error_occurred) }
                }
                showDialog(
                    title,
                    message,
                    positiveActionText,
                    negativeActionText,
                    positiveAction,
                    negativeAction
                )
            }

            else -> {
                title = resources.getString(R.string.error)
                message = errorObject?.message.toString().ifEmpty { resources.getString(R.string.error_occurred) }

                showDialog(
                    title,
                    message,
                    positiveActionText,
                    negativeActionText,
                    positiveAction,
                    negativeAction
                )
            }
        }
    }

    protected fun showInfoPopUp(
        message: String
    ) {
        showDialog("", message, getString(R.string.ok), "", {}, {})
    }

    private fun showDialog(
        title: String,
        message: String,
        positiveActionText: String,
        negativeActionText: String,
        positiveAction: () -> Unit,
        negativeAction: () -> Unit
    ) {
        val dialog: ErrorBottomSheet? = ErrorBottomSheet.newInstance(
            title,
            message,
            positiveActionText,
            negativeActionText,
            object : ErrorBottomSheet.ActionListener {
                override fun onPositiveActionClicked() {
                    positiveAction()
                }

                override fun onNegativeActionClicked() {
                    negativeAction()
                }
            })
        dialog?.isCancelable = false
        dialog?.show(supportFragmentManager, "dialog")
    }

    companion object {
        const val MAX_UI_INCREASE_RATIO = 1.3f
    }
}

