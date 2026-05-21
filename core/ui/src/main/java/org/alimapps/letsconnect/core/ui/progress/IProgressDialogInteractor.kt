package org.alimapps.letsconnect.core.ui.progress

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import java.lang.ref.WeakReference

interface IProgressDialogInteractor {
    fun registerDialogInitFragment(fragment: WeakReference<Fragment>) {}
    fun registerDialogInitActivity(activity: WeakReference<AppCompatActivity>) {}
    
    fun showProgressDialog() {}
    fun hideProgressDialog() {}
}