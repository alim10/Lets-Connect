package org.alimapps.letsconnect.convention.internal

import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.LibraryExtension
import com.android.build.api.dsl.CommonExtension
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.withType
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

internal fun Project.configureAndroid(
    commonExtension: CommonExtension
) {
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
            jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_17)
        }
    }

    dependencies {
        add("implementation", getLibBundle("group.android.ui"))
    }
}



/*
internal inline fun <reified T : Any> Project.configureAndroid() {
    extensions.configure<T> {
        when (this) {
            is ApplicationExtension -> configureAndroidCommon(this)
            is LibraryExtension -> configureAndroidCommon(this)
        }
    }
}

internal fun Project.configureAndroidCommon(
    commonExtension: Any,
) {
    with(pluginManager) {
        apply("org.jetbrains.kotlin.plugin.compose")
        apply("kotlin-parcelize")
    }

    when (commonExtension) {
        is ApplicationExtension -> {
            commonExtension.apply {
                compileSdk = 36
                defaultConfig {
                    minSdk = 26
                }
                compileOptions {
                    sourceCompatibility = JavaVersion.VERSION_21
                    targetCompatibility = JavaVersion.VERSION_21
                }
                buildFeatures {
                    viewBinding = true
                    buildConfig = true
                }
                namespace = project.generateNamespace()
            }
        }
        is LibraryExtension -> {
            commonExtension.apply {
                compileSdk = 36
                defaultConfig {
                    minSdk = 26
                }
                compileOptions {
                    sourceCompatibility = JavaVersion.VERSION_21
                    targetCompatibility = JavaVersion.VERSION_21
                }
                buildFeatures {
                    viewBinding = true
                    buildConfig = true
                }
                namespace = project.generateNamespace()
            }
        }
    }
}
*/
