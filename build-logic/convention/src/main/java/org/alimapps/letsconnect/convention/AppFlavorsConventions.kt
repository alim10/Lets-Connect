package org.alimapps.letsconnect.convention

import com.android.build.gradle.BaseExtension
import org.gradle.api.Plugin
import org.gradle.api.Project

class AppFlavorsConventions : Plugin<Project> {

    override fun apply(target: Project) {

        val appId = "org.alimapps.letsconnect"

        val extension = target.extensions.getByName("android")
        if (extension is BaseExtension) {
            extension.apply {
                flavorDimensions("build", "vendor")

                productFlavors {
                    create("letsConnectQa") {
                        dimension = "build"
                        buildConfigField("int", "VERSION_CODE", "${target.property("VERSION_CODE")}")
                        buildConfigField("String", "VERSION_NAME", "${target.property("VERSION_NAME")}")
                        val fileProvider = "${appId}.qa.provider"
                        manifestPlaceholders["apphost"] = "dev-identity.lbsnetwork.net"
                        manifestPlaceholders["fileProvider"] = fileProvider
                    }
                    create("letsConnectStaging") {
                        dimension = "build"
                        buildConfigField(
                            "int",
                            "VERSION_CODE",
                            "${target.property("VERSION_CODE")}"
                        )
                        buildConfigField(
                            "String",
                            "VERSION_NAME",
                            "${target.property("VERSION_NAME")}"
                        )
                        val fileProvider = "${appId}.qa.provider"
                        manifestPlaceholders["apphost"] = "dev-identity.lbsnetwork.net"
                        manifestPlaceholders["fileProvider"] = fileProvider
                    }
                    create("letsConnectDev") {
                        dimension = "build"
                        buildConfigField(
                            "int",
                            "VERSION_CODE",
                            "${target.property("VERSION_CODE")}"
                        )
                        buildConfigField(
                            "String",
                            "VERSION_NAME",
                            "${target.property("VERSION_NAME")}"
                        )
                        val fileProvider = "${appId}.qa.provider"
                        manifestPlaceholders["apphost"] = "dev-identity.lbsnetwork.net"
                        manifestPlaceholders["fileProvider"] = fileProvider
                    }
                    create("letsConnectProd") {
                        dimension = "build"
                        buildConfigField(
                            "int",
                            "VERSION_CODE",
                            "${target.property("VERSION_CODE")}"
                        )
                        buildConfigField(
                            "String",
                            "VERSION_NAME",
                            "${target.property("VERSION_NAME")}"
                        )
                        val fileProvider = "${appId}.provider"
                        manifestPlaceholders["apphost"] = "dev-identity.lbsnetwork.net"
                        manifestPlaceholders["fileProvider"] = fileProvider
                    }
                    create("gms") {
                        dimension = "vendor"
                    }
                    create("hms") {
                        dimension = "vendor"
                    }
                }
            }
        }
    }
}