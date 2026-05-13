package org.alimapps.letsconnect.convention.internal

import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

internal fun Project.configureRoom() {
    with(pluginManager) {
        apply("com.google.dagger.hilt.android")
        apply("com.google.devtools.ksp")
    }

    dependencies {
        add("implementation", getLibrary("androidx.room.ktx"))
        add("implementation", getLibrary("androidx.room.runtime"))
        add("ksp", getLibrary("androidx.room.compiler"))
    }
}