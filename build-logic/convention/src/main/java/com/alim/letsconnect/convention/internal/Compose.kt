package com.alim.letsconnect.convention.internal

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

internal inline fun <reified T : CommonExtension<*, *, *, *, *, *>> Project.configureCompose() {
    extensions.configure<T> {
        buildFeatures {
            compose = true
        }
        composeOptions {
            kotlinCompilerExtensionVersion = "1.5.0"
        }
    }

    dependencies {
        add("implementation", platform(getLibrary("androidx.compose.bom")))
        add("implementation", getLibrary("androidx.compose.ui"))
        add("implementation", getLibrary("androidx.compose.graphics"))
        add("implementation", getLibrary("androidx.compose.preview"))
        add("implementation", getLibrary("androidx.compose.material"))
        add("implementation", getLibrary("androidx.compose.icons"))
        add("implementation", getLibrary("coil.compose"))
        add("implementation", getLibrary("androidx.lifecycle"))
        add("implementation", getLibrary("androidx.activity"))
        add("implementation", getLibrary("androidx.browser"))
        add("debugImplementation", getLibrary("androidx.compose.ui.tooling"))
    }
}