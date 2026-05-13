package org.alimapps.letsconnect.convention.internal

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

internal inline fun <reified T : CommonExtension<*, *, *, *, *, *>> Project.configureAndroid() {
    with(pluginManager) {
        apply("org.jetbrains.kotlin.android")
        apply("org.jetbrains.kotlin.plugin.compose")
        apply("kotlin-parcelize")
    }

    extensions.configure<T> {
        compileSdk = 36
        defaultConfig {
            minSdk = 26
        }

        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_17
            targetCompatibility = JavaVersion.VERSION_17
        }
        buildFeatures {
           viewBinding = true
           buildConfig = true
        }
        namespace = project.generateNamespace()
    }
}