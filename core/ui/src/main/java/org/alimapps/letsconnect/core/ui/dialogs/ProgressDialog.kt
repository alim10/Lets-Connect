package org.alimapps.letsconnect.core.ui.dialogs

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import org.alimapps.letsconnect.core.common.logging.debug
import org.alimapps.letsconnect.core.ui.R
import javax.inject.Inject

class ProgressDialog @Inject constructor(context: Context) : Dialog(context) {

    init {
        debug("[ProgressDialog] add observers ${this.hashCode()}")
        setCancelable(false)
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        setContentView(R.layout.loading_dialog)
    }

    override fun show() {
        super.show()
        debug("[ProgressDialog] called show ${this.hashCode()}")
    }

    override fun hide() {
        debug("[ProgressDialog] called hide ${this.hashCode()}")
        if (isShowing){
            debug("[ProgressDialog] dismiss")
            dismiss()
        }
    }
}