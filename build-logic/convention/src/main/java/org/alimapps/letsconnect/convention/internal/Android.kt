package org.alimapps.letsconnect.convention.internal

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

/**
 * Configure basic Android settings (SDK, namespace, etc.)
 * Does NOT include implementation dependencies or UI-specific plugins.
 */
internal fun Project.configureAndroid(commonExtension: CommonExtension) {
    with(pluginManager) {
        apply("kotlin-parcelize")
    }
    commonExtension.apply {
        compileSdk = 37

        defaultConfig.minSdk = 26

        buildFeatures.apply {
            viewBinding = true
            buildConfig = true
        }
        namespace = generateNamespace()
    }

    configureKotlinAndroid(commonExtension)
}

/**
 * Configure shared Android dependencies for modules that need them.
 */
internal fun Project.configureAndroidDependencies() {
    dependencies {
        add("implementation", getLibBundle("group.android.ui"))
    }
}
