package com.alim.letsconnect.convention.internal

import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

internal fun Project.configureHilt() {
    with(pluginManager) {
        apply("dagger.hilt.android.plugin")
    }

    dependencies {
        add("implementation", getLibrary("dagger.hilt"))
        add("kapt", getLibrary("dagger.hilt.compiler"))
        add("kapt", getLibrary("dagger.hilt.android.compiler"))
    }
}