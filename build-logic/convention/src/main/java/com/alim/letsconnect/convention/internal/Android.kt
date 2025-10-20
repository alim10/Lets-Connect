package com.alim.letsconnect.convention.internal

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.getByType
import org.jetbrains.kotlin.gradle.plugin.KaptExtension

internal inline fun <reified T : CommonExtension<*, *, *, *, *, *>> Project.configureAndroid() {
    with(pluginManager) {
        apply("org.jetbrains.kotlin.android")
        apply("org.jetbrains.kotlin.kapt")
        apply("kotlin-parcelize")
    }

    extensions.configure<T> {
        compileSdk = 35

        defaultConfig {
            minSdk = 24
        }

        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_17
            targetCompatibility = JavaVersion.VERSION_17
        }
    }

    val kaptExtension = extensions.getByType<KaptExtension>()
    kaptExtension.apply {
        correctErrorTypes = true
    }
}