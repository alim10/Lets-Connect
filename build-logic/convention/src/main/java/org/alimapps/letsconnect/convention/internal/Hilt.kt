package org.alimapps.letsconnect.convention.internal

import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

internal fun Project.configureHilt() {
    with(pluginManager) {
        apply("com.google.dagger.hilt.android")
        apply("com.google.devtools.ksp")
    }

    dependencies {
        add("implementation", getLibrary("dagger.hilt.android"))
        add("ksp", getLibrary("dagger.hilt.android.compiler"))
    }
}