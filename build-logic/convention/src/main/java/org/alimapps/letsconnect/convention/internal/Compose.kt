package org.alimapps.letsconnect.convention.internal

import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.CommonExtension
import com.android.build.api.dsl.LibraryExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies


internal fun Project.configureCompose(
    commonExtension: CommonExtension
) {
    dependencies {
        add("implementation", platform(getLibrary("androidx.compose.bom")))
        add("implementation", getLibrary("androidx.compose.ui"))
        add("implementation", getLibrary("androidx.compose.graphics"))
        add("implementation", getLibrary("androidx.compose.preview"))
        add("implementation", getLibrary("androidx.compose.material3"))
        add("implementation", getLibrary("androidx.compose.icons"))
        add("implementation", getLibrary("coil.compose"))
        add("implementation", getLibrary("androidx.lifecycle.runtime.ktx"))
        add("implementation", getLibrary("androidx.compose.activity"))
//        add("implementation", getLibrary("androidx.browser"))
        add("debugImplementation", getLibrary("androidx.compose.ui.tooling"))
    }
}

/*
internal inline fun <reified T : Any> Project.configureCompose() {
    extensions.configure<T> {
        when (this) {
            is ApplicationExtension -> {
                buildFeatures {
                    compose = true
                }
                composeOptions {
                    kotlinCompilerExtensionVersion = "1.5.0"
                }
            }
            is LibraryExtension -> {
                buildFeatures {
                    compose = true
                }
                composeOptions {
                    kotlinCompilerExtensionVersion = "1.5.0"
                }
            }
        }
    }

    dependencies {
        add("implementation", platform(getLibrary("androidx.compose.bom")))
        add("implementation", getLibrary("androidx.compose.ui"))
        add("implementation", getLibrary("androidx.compose.graphics"))
        add("implementation", getLibrary("androidx.compose.preview"))
        add("implementation", getLibrary("androidx.compose.material3"))
        add("implementation", getLibrary("androidx.compose.icons"))
        add("implementation", getLibrary("coil.compose"))
        add("implementation", getLibrary("androidx.lifecycle.runtime.ktx"))
        add("implementation", getLibrary("androidx.compose.activity"))
//        add("implementation", getLibrary("androidx.browser"))
        add("debugImplementation", getLibrary("androidx.compose.ui.tooling"))
    }
}
*/
