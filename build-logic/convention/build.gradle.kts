plugins {
    `kotlin-dsl`
}

group = "com.alim.letsconnect.convention"

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

}

gradlePlugin {
    plugins {
        register("appModule") {
            id = "convention.appModule"
            implementationClass = "com.alim.letsconnect.convention.AppModuleConventions"
        }
        register("dataModule") {
            id = "convention.dataModule"
            implementationClass = "com.alim.letsconnect.convention.DataModuleConventions"
        }
//        register("featureModule") {
//            id = "convention.featureModule"
//            implementationClass = "com.alim.letsconnect.convention.FeatureModuleConventions"
//        }
        register("uiModule") {
            id = "convention.uiModule"
            implementationClass = "com.alim.letsconnect.convention.UiModuleConventions"
        }
        register("appFlavors") {
            id = "convention.appFlavors"
            implementationClass = "com.alim.letsconnect.convention.AppFlavorsConventions"
        }
        register("appRefreshDependencies") {
            id = "convention.appRefreshDependencies"
            implementationClass = "com.alim.letsconnect.convention.task.RefreshDependencies"
        }
    }
}
