plugins {
    `kotlin-dsl`
}

group = "org.alimapps.letsconnect.convention"

java {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
}
kotlin {
   jvmToolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
    jvmToolchain(21)
}

dependencies {
    compileOnly(libs.android.tools.build.gradle.plugin)
    compileOnly(libs.kotlin.gradle.plugin)
    compileOnly(libs.ksp.gradlePlugin)
}

gradlePlugin {
    plugins {
        register("appplicationModule") {
            id = "convention.appplicationModule"
            implementationClass = "org.alimapps.letsconnect.convention.AppModuleConventions"
        }
        register("dataModule") {
            id = "convention.dataModule"
            implementationClass = "org.alimapps.letsconnect.convention.DataModuleConventions"
        }
        register("featureModule") {
            id = "convention.featureModule"
            implementationClass = "org.alimapps.letsconnect.convention.FeatureModuleConventions"
        }
        register("uiModule") {
            id = "convention.uiModule"
            implementationClass = "org.alimapps.letsconnect.convention.UiModuleConventions"
        }
        register("appFlavors") {
            id = "convention.appFlavors"
            implementationClass = "org.alimapps.letsconnect.convention.AppFlavorsConventions"
        }
        register("appFlavorsEndPoint") {
            id = "convention.appFlavorsEndPoint"
            implementationClass = "org.alimapps.letsconnect.convention.AppFlavorsEndPointConvention"
        }
        register("appRefreshDependencies") {
            id = "convention.appRefreshDependencies"
            implementationClass = "org.alimapps.letsconnect.convention.task.RefreshDependencies"
        }
    }
}
