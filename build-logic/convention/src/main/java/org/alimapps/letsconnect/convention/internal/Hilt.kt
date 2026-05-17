package org.alimapps.letsconnect.convention.internal

import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

//internal fun Project.configureHilt() {
//    with(pluginManager) {
//        apply("com.google.dagger.hilt.android")
//        apply("com.google.devtools.ksp")
//    }
//
//    dependencies {
//        add("implementation", getLibrary("hilt.android"))
//        add("ksp", getLibrary("hilt.android.compiler"))
//    }
//}


internal fun Project.configureHilt() {
    pluginManager.apply("com.google.devtools.ksp")

    val hiltAction = {
        pluginManager.apply("com.google.dagger.hilt.android")
        dependencies {
            add("implementation", getLibrary("hilt.android"))
            add("ksp", getLibrary("hilt.android.compiler"))
        }
    }

    pluginManager.withPlugin("com.android.application") { hiltAction() }
    pluginManager.withPlugin("com.android.library") { hiltAction() }
}

