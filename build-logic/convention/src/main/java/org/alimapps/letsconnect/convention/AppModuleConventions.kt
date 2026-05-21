package org.alimapps.letsconnect.convention

import com.android.build.api.dsl.ApplicationExtension
import org.alimapps.letsconnect.convention.internal.configureAndroid
import org.alimapps.letsconnect.convention.internal.configureAndroidDependencies
import org.alimapps.letsconnect.convention.internal.configureCompose
import org.alimapps.letsconnect.convention.internal.configureRoom
import org.alimapps.letsconnect.convention.internal.configureHilt
import org.alimapps.letsconnect.convention.internal.configureNavigation
import org.alimapps.letsconnect.convention.internal.configureUnitTest
import org.alimapps.letsconnect.convention.internal.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class AppModuleConventions : Plugin<Project> {
    
	override fun apply(target: Project) = with(target) {
        with(pluginManager) {
            apply("com.android.application")
            apply("com.google.gms.google-services")
            apply("com.google.firebase.crashlytics")
        }

        extensions.configure<ApplicationExtension> {
            defaultConfig {
                applicationId = "org.alimapps.letsconnect"
                testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
                vectorDrawables {
                    useSupportLibrary = true
                }
                versionCode = project.property("VERSION_CODE").toString().toInt()
                versionName = project.property("VERSION_NAME").toString()
            }
            configureAndroid(this)
            configureAndroidDependencies()
            configureCompose(this)
            configureUnitTest(this)
        }
        configureRoom()
        configureHilt()
        configureNavigation()
    }
}
