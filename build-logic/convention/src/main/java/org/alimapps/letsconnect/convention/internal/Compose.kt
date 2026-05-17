package org.alimapps.letsconnect.convention.internal

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies


internal fun Project.configureCompose(
    commonExtension: CommonExtension
) {
    dependencies{
        add("implementation", platform(getLibrary("androidx.compose.bom")))
        add("implementation", getLibBundle("group.compose.ui"))
        add("debugImplementation", getLibrary("androidx.compose.ui.tooling"))
    }
}
