/*
package org.alimapps.letsconnect.convention.internal

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

internal fun Project.configureAndroidUI(commonExtension: CommonExtension) {
    with(pluginManager) {
        apply("org.jetbrains.kotlin.plugin.compose")
        apply("kotlin-parcelize")
    }

    commonExtension.apply {
        compileSdk = 37

        defaultConfig.minSdk = 26

        compileOptions.apply {
            sourceCompatibility = JavaVersion.VERSION_17
            targetCompatibility = JavaVersion.VERSION_17
        }

        buildFeatures.apply {
            viewBinding = true
            buildConfig = true
        }
        namespace = generateNamespace()
    }

    tasks.withType<KotlinCompile>().configureEach {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_17)
        }
    }

    dependencies {
        add("implementation", platform(getLibrary("androidx-compose-bom")))
        add("implementation", getLibBundle("group.android.ui"))
//        add("implementation", getLibrary("androidx-compose-runtime"))
    }
}*/
