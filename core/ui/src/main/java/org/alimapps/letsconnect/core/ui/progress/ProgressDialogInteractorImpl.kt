package org.alimapps.letsconnect.core.ui.progress

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import org.alimapps.letsconnect.core.ui.dialogs.ProgressDialog
import java.lang.ref.WeakReference

/**
 * @sample [IProgressDialogInteractor by ProgressDialogInteractorImpl()]
 * @author Zeyad Alsayed
 * @since 5/2023
 *
 * User can delegate showing and hide our custom [ProgressDialog]
 * safely without concerning about MemoryLeak or Lifecycle aware or
 * even running on UI thread.
 * @see [IProgressDialogInteractor by ProgressDialogInteractorImpl()]
* */
class ProgressDialogInteractorImpl: IProgressDialogInteractor,
    LifecycleObserver,
    LifecycleEventObserver {
    
    private var mFragmentRef: WeakReference<Fragment>? = null
    private var mActivityRef: WeakReference<AppCompatActivity>? = null
    private var progressDialog: ProgressDialog? = null
    
    override fun registerDialogInitActivity(activity: WeakReference<AppCompatActivity>) {
        mActivityRef = activity
        mActivityRef?.get()?.lifecycle?.addObserver(this)
        progressDialog = ProgressDialog(mActivityRef?.get()!!)
    }
    
    override fun registerDialogInitFragment(fragment: WeakReference<Fragment>) {
        mFragmentRef = fragment
        mFragmentRef?.get()?.viewLifecycleOwner?.lifecycle?.addObserver(this)
        progressDialog = ProgressDialog(mFragmentRef?.get()?.requireContext()!!)
    }
    
    override fun hideProgressDialog() {
        progressDialog?.hide()
    }
    
    override fun showProgressDialog() {
        progressDialog?.show()
    }
    
    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
        when (event) {
            Lifecycle.Event.ON_CREATE -> {}
            Lifecycle.Event.ON_START -> {}
            Lifecycle.Event.ON_RESUME -> {}
            Lifecycle.Event.ON_PAUSE -> {
                progressDialog?.dismiss()
                progressDialog?.cancel()
            }
            Lifecycle.Event.ON_STOP -> {
                progressDialog?.dismiss()
                progressDialog?.cancel()
            }
            Lifecycle.Event.ON_DESTROY -> {
                try {
                    progressDialog?.dismiss()
                    progressDialog?.cancel()
                } catch (e: Exception) { println(e.stackTrace) }
                mFragmentRef = null
                mActivityRef = null
                progressDialog = null
            }
            else -> {}
        }
    }
}