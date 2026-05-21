package org.alimapps.letsconnect.convention

import com.android.build.api.dsl.LibraryExtension
import org.alimapps.letsconnect.convention.internal.configureAndroid
import org.alimapps.letsconnect.convention.internal.configureCompose
import org.alimapps.letsconnect.convention.internal.configureHilt
import org.alimapps.letsconnect.convention.internal.configureLibrary
import org.alimapps.letsconnect.convention.internal.configureNavigation
import org.alimapps.letsconnect.convention.internal.configureUnitTest
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class LibraryModuleConventions : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        pluginManager.apply("com.android.library")

        extensions.configure<LibraryExtension> {
            configureLibrary(this)
            configureUnitTest(this)
        }

        configureHilt()
    }
}