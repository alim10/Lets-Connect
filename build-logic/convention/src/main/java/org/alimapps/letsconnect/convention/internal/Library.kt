package org.alimapps.letsconnect.convention.internal

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project

/**
 * Configure standard library module settings.
 */
internal fun Project.configureLibrary(commonExtension: CommonExtension) {
    configureAndroid(commonExtension)
    configureAndroidDependencies()
}
