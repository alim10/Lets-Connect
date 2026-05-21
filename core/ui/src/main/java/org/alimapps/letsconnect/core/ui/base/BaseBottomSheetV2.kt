package org.alimapps.letsconnect.core.ui.base
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import com.google.android.material.R
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.firebase.analytics.FirebaseAnalytics
import org.alimapps.letsconnect.core.analytics.Analytics
import org.alimapps.letsconnect.core.ui.progress.IProgressDialogInteractor
import org.alimapps.letsconnect.core.ui.progress.ProgressDialogInteractorImpl
import java.lang.ref.WeakReference
import javax.inject.Inject

private const val TAG = "BaseBottomSheetV2"

abstract class BaseBottomSheetV2<out VB : ViewBinding> : BottomSheetDialogFragment(),
    IProgressDialogInteractor by ProgressDialogInteractorImpl() {

    open val isDismissible = true
    open val isFullHeight = false
    open val isDraggable = true
    open val maxHeightPercentage: Double? = null

    lateinit var mNavController: NavController

    abstract val bindingInflater: (LayoutInflater) -> VB
    private var _binding: ViewBinding? = null

    protected val binding: VB
        get() = _binding as VB

    @Inject
    lateinit var statisticAnalytics: Analytics

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.setOnShowListener {
            val bottomSheetDialog = it as BottomSheetDialog
            val parentLayout =
                bottomSheetDialog.findViewById<View>(R.id.design_bottom_sheet)
            parentLayout?.let {
                val behaviour = BottomSheetBehavior.from(it)
                if (isFullHeight) setupFullHeight(it) else setupWrapHeight(it)
                behaviour.state = BottomSheetBehavior.STATE_EXPANDED
                behaviour.isDraggable = isDraggable
            }
        }
        dialog.window?.setGravity(Gravity.BOTTOM)
        return dialog
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        registerDialogInitFragment(WeakReference(this))
        _binding = bindingInflater.invoke(layoutInflater)
        return _binding?.root!!
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        isCancelable = isDismissible
        try {
            statisticAnalytics.logCustomEvent(
                FirebaseAnalytics.Event.SCREEN_VIEW,
                bundleOf(
                    FirebaseAnalytics.Param.SCREEN_NAME to this.javaClass.simpleName,
                    FirebaseAnalytics.Param.SCREEN_CLASS to this.javaClass.simpleName
                )
            )
        } catch (e: Exception) {
            Log.e(TAG, "Screen Name Exception: ${this.javaClass.simpleName}")
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        registerDialogInitFragment(WeakReference(this))
        maxHeightPercentage?.let { percentage ->
            dialog?.context?.resources?.displayMetrics?.heightPixels?.let { height ->
                view.layoutParams.height = (height * percentage).toInt()
            }
        }
        Log.e("BaseBottomSheetV2", "Screen Name >>> ${this::class.java.name}")

        runCatching { mNavController = findNavController() }
        setUpUiViews()
        setOnClickListeners()
    }

    open fun setOnClickListeners() {}
    protected open fun setUpUiViews(): VB? = null
    override fun onDismiss(dialog: DialogInterface) {
        if (isDismissible) {
            dismissAllowingStateLoss()
            dismissNow()
        }
        super.onDismiss(dialog)
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    private fun setupWrapHeight(bottomSheet: View) {
        val layoutParams = bottomSheet.layoutParams
        layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT
        bottomSheet.layoutParams = layoutParams
    }

    private fun setupFullHeight(bottomSheet: View) {
        val layoutParams = bottomSheet.layoutParams
        layoutParams.height = WindowManager.LayoutParams.MATCH_PARENT
        bottomSheet.layoutParams = layoutParams
    }

    open fun showLoadingDialog(isLoading: Boolean) {
        if (isLoading) showProgressDialog()
        else hideProgressDialog()
    }
}
