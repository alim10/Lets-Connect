package org.alimapps.letsconnect.convention.internal

import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.getByType

internal val Project.libs
    get(): VersionCatalog = extensions.getByType<VersionCatalogsExtension>().named("libs")

internal fun Project.getLibrary(name: String) = libs.findLibrary(name).get()

internal fun Project.getLibBundle(name: String) = libs.findBundle(name).get()

internal fun Project.generateNamespace(): String {
    val prefix = "org.alimapps.letsconnect"
    val suffix = path.replace(":", ".").lowercase()
    return if (suffix == ".app") prefix else "$prefix$suffix"
}