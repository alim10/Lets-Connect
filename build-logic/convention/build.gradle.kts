plugins {
    `kotlin-dsl`
}

group = "org.alimapps.letsconnect.convention"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}
kotlin {
/*    jvmToolchain {
        languageVersion.set(JavaLanguageVersion.of(<MAJOR_JDK_VERSION>))
    }
    // Or shorter:
    jvmToolchain(<MAJOR_JDK_VERSION>)*/
    // For example:
//    jvmToolchain(17)
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
