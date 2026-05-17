package org.alimapps.letsconnect.convention.task

import org.gradle.api.Plugin
import org.gradle.api.Project
import java.io.File
import java.time.Duration
import java.time.LocalDateTime

class RefreshDependencies : Plugin<Project> {
    override fun apply(target: Project) {
        val refreshTask = target.tasks.register("RefreshDependencies") {
            val cacheDir = File(target.gradle.gradleUserHomeDir, "caches/modules-2/files-2.1")

            val currentTimeOfStart = LocalDateTime.now()
            target.configurations.forEachIndexed { index, config ->
                if (true) return@forEachIndexed // USE TO DISABLE CLEARING CACHE AND COMMENT OTHERS
                if(index == 0) println("Start refreshing dependencies in $currentTimeOfStart")
                config.dependencies.forEach { dependency ->
                    /**
                     * @sample [org.alimapps.letsconnect:SHCoreModuleGMS:3.22.0]
                     * @param [org.alimapps.letsconnect] Represent dependency GROUP
                     * @param [org.alimapps.letsconnect]  Represent dependency NAME
                     * @param [1.0.1]           Represent dependency VERSION
                     * And refresh them with new fresh install
                     * And <install fresh again>
                     * */
                    if (dependency.group?.contains("org.alimapps.letsconnect") == false) return@forEach //USE IT CAREFULLY

                    val group = dependency.group//parts[0]//.replace(".", "/")
                    val artifact = dependency.name
                    val version = dependency.version

                    val dependencyDir = File(cacheDir, "$group/$artifact/$version")
                    if (dependencyDir.exists()) {
                        dependencyDir.deleteRecursively()
                    }
                }
                if (index == target.configurations.size -1) {
                    println("End refreshing dependencies in ${LocalDateTime.now()}")
                    println("Finished refreshing dependencies and took ${Duration.between(currentTimeOfStart, LocalDateTime.now()).seconds} Seconds !!")
                }
            }
        }
        target.tasks.named("preBuild").configure {
            dependsOn(refreshTask)
        }
    }
}