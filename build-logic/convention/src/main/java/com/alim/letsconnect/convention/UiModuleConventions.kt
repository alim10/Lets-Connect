package com.alim.letsconnect.convention

import com.android.build.api.dsl.LibraryExtension
import com.alim.letsconnect.convention.internal.configureAndroid
import com.alim.letsconnect.convention.internal.configureCompose
import com.alim.letsconnect.convention.internal.configureNavigation
import org.gradle.api.Plugin
import org.gradle.api.Project

class UiModuleConventions : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        with(pluginManager) {
            apply("com.android.library")
        }

        configureAndroid<LibraryExtension>()
        configureCompose<LibraryExtension>()
        configureNavigation()
    }
}