package com.alim.letsconnect.convention

import com.android.build.api.dsl.LibraryExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import com.alim.letsconnect.convention.internal.configureAndroid
import com.alim.letsconnect.convention.internal.configureCompose
import com.alim.letsconnect.convention.internal.configureHilt
import com.alim.letsconnect.convention.internal.configureNavigation
import com.alim.letsconnect.convention.internal.configureUnitTest

class FeatureModuleConventions : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        with(pluginManager) {
            apply("com.android.library")
        }

        configureAndroid<LibraryExtension>()
        configureHilt()
        configureUnitTest()
        configureCompose<LibraryExtension>()
        configureNavigation()
    }
}