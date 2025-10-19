package com.alim.letsconnect.convention.internal

import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

internal fun Project.configureNavigation() {
    dependencies {
        add("implementation",getLibrary("androidx.compose.navigation"))
        add("implementation", getLibrary("androidx.hilt.navigation.compose"))
    }
}