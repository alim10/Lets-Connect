package org.alimapps.letsconnect.convention.internal

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

internal fun Project.configureUnitTest(
    commonExtension: CommonExtension,
) {
    dependencies {
        add("testImplementation", getLibBundle("group.test.unit"))
        add("testImplementation", getLibBundle("group.compose.test"))
        add("androidTestImplementation", getLibBundle("group.android.test"))
    }
}