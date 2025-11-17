package org.alimapps.letsconnect.convention

import com.android.build.api.dsl.LibraryExtension
import org.alimapps.letsconnect.convention.internal.configureAndroid
import org.alimapps.letsconnect.convention.internal.configureHilt
import org.alimapps.letsconnect.convention.internal.configureUnitTest
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