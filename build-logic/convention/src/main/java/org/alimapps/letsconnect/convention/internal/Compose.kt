package org.alimapps.letsconnect.convention.internal

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

/**
 * Configure Compose settings, plugin, and dependencies.
 */
internal fun Project.configureCompose(
    commonExtension: CommonExtension
) {
    with(pluginManager) {
        apply("org.jetbrains.kotlin.plugin.compose")
    }

    dependencies {
        add("implementation", platform(getLibrary("androidx-compose-bom")))
        add("implementation", getLibBundle("group.compose.ui"))
        add("implementation", getLibrary("androidx-compose-runtime"))
        add("debugImplementation", getLibrary("androidx-compose-ui-tooling"))
    }
}
