package org.alimapps.letsconnect.convention.internal

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

internal fun Project.configureUnitTest() {
    dependencies {
        add("testImplementation", getLibBundle("test.unit"))
        add("testImplementation", getLibBundle("compose.test"))
//        add("testImplementation", getLibrary("kotlinx.coroutines.test"))
//        add("testImplementation", getLibrary("turbine"))
        add("androidTestImplementation", getLibBundle("android.test"))
    }
}