package org.alimapps.letsconnect.auth

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.alimapps.letsconnect.core.navigations.Navigator

class ForgotPassActivity : AppCompatActivity() {
    companion object{
        fun launchActivity(activity: Activity) {
            val intent = Intent(activity, ForgotPassActivity::class.java)
            activity.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_pass)
    }
}

object GoToForgotActivity : Navigator {

    override fun navigate(activity: Activity) {
        AuthActivity.launchActivity(activity)
    }

}