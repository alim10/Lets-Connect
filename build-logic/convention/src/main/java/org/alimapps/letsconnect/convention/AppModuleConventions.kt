package org.alimapps.letsconnect.convention

import com.android.build.api.dsl.ApplicationExtension
import org.alimapps.letsconnect.convention.internal.configureAndroid
import org.alimapps.letsconnect.convention.internal.configureCompose
import org.alimapps.letsconnect.convention.internal.configureRoom
import org.alimapps.letsconnect.convention.internal.configureHilt
import org.alimapps.letsconnect.convention.internal.configureNavigation
import org.alimapps.letsconnect.convention.internal.configureUnitTest
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class AppModuleConventions : Plugin<Project> {
    
//	override fun apply(target: Project) = with(target) {
//        pluginManager.apply("com.android.application")
//
//        extensions.configure<ApplicationExtension> {
//            defaultConfig {
//                applicationId = "org.alimapps.letsconnect"
//                targetSdk = 35
//                versionCode = 1
//                versionName = "1.0"
//                testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
//                vectorDrawables {
//                    useSupportLibrary = true
//                }
//                versionCode = project.property("VERSION_CODE").toString().toInt()
//                versionName = project.property("VERSION_NAME").toString()
//            }
//            configureAndroid(this)
//            configureCompose(this)
//            configureUnitTest(this)
//        }
//        configureRoom()
//        configureHilt()
//        configureNavigation()
//    }



    override fun apply(target: Project) = with(target) {
        with(pluginManager) {
            apply("com.android.application")
        }

        extensions.configure<ApplicationExtension> {
            defaultConfig {
                targetSdk = 36
                multiDexEnabled = true
                vectorDrawables.useSupportLibrary = true

                testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

//                versionCode = project.property("VERSION_CODE").toString().toInt()
//                versionName = project.property("VERSION_NAME").toString()
            }

            packaging {
                resources {
                    excludes += "/META-INF/{AL2.0,LGPL2.1}"
                }
            }

            configureAndroid(this)
            configureCompose(this)
            configureUnitTest(this)
            configureHilt()
            configureRoom()
            configureNavigation()
        }


    }
}
