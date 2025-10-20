package com.alim.letsconnect.convention

import com.android.build.api.dsl.LibraryExtension
import com.alim.letsconnect.convention.internal.configureAndroid
import com.alim.letsconnect.convention.internal.configureHilt
import com.alim.letsconnect.convention.internal.configureUnitTest
import org.gradle.api.Plugin
import org.gradle.api.Project

class DataModuleConventions : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        with(pluginManager) {
            apply("com.android.library")
        }

        configureAndroid<LibraryExtension>()
        configureHilt()
        configureUnitTest()
    }
}