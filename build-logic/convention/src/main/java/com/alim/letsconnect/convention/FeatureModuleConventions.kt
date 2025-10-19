package com.alim.letsconnect.convention

import com.android.build.api.dsl.LibraryExtension
import com.android.goalak.convention.internal.configureAndroid
import com.android.goalak.convention.internal.configureCompose
import com.android.goalak.convention.internal.configureHilt
import com.android.goalak.convention.internal.configureNavigation
import com.android.goalak.convention.internal.configureUnitTest
import org.gradle.api.Plugin
import org.gradle.api.Project

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