package org.alimapps.letsconnect.convention

import com.android.build.api.dsl.LibraryExtension
import org.alimapps.letsconnect.convention.internal.configureAndroid
import org.alimapps.letsconnect.convention.internal.configureCompose
import org.alimapps.letsconnect.convention.internal.configureNavigation
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