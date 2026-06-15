package org.alimapps.letsconnect.core.ui.bottomsheet
import android.text.Editable
import android.view.LayoutInflater
import org.alimapps.letsconnect.core.ui.base.BaseBottomSheetV2
import org.alimapps.letsconnect.core.ui.databinding.DialogLayoutBinding
import org.alimapps.letsconnect.core.ui.extention.gone
import org.alimapps.letsconnect.core.ui.extention.visible

class ErrorBottomSheet : BaseBottomSheetV2<DialogLayoutBinding>() {
    override val bindingInflater: (LayoutInflater) -> DialogLayoutBinding
        get() = DialogLayoutBinding::inflate
    // Declare those two so we can destroy it into onDestroyView to prevent memory leaks
    private var errorTitle: String? = null
    private var errorMessage: String? = null
    private var positiveAction: String? = null
    private var negativeAction: String? = null
    private var actionListener: ActionListener? = null

    override val isDismissible: Boolean
        get() = false

    override val isDraggable: Boolean
        get() = false

    override fun setUpUiViews() = binding.apply {
        if (errorTitle.isNullOrEmpty()) {
            dialogTitleTextview.gone()
        } else {
            dialogTitleTextview.text = errorTitle as Editable?
            dialogTitleTextview.visible()
        }
        if (errorMessage.isNullOrEmpty()) {
            dialogMessageTextview.gone()
        } else {
            dialogMessageTextview.text = errorMessage as Editable?
            dialogMessageTextview.visible()
        }
    }
    
    override fun setOnClickListeners() {
        binding.apply {
            btnOk.setOnClickListener {
                isDialogShown = false
                dismiss()
                actionListener?.onPositiveActionClicked()
            }
        }
    }

    interface ActionListener {
        fun onPositiveActionClicked()
        fun onNegativeActionClicked()
    }

    companion object {
        private var isDialogShown = false

        fun newInstance(
            errorTitle: String? = "",
            errorMessage: String? = "",
            positiveAction: String? = "",
            negativeAction: String? = "",
            actionListener: ActionListener? = null
        ): ErrorBottomSheet? {
            return if (!isDialogShown) {
                val objDialog = ErrorBottomSheet()
                objDialog.errorTitle = errorTitle
                objDialog.errorMessage = errorMessage
                objDialog.positiveAction = positiveAction
                objDialog.negativeAction = negativeAction
                isDialogShown = true
                objDialog.actionListener = actionListener
                objDialog
            } else {
                null
            }
        }
    }
}