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
        register("dataModule") {
            id = "buildlogic.datamodule"
            implementationClass = "com.alim.letsconnect.convention.DataModuleConventions"
        }
        register("featureModule") {
            id = "buildlogic.featuremodule"
            implementationClass = "com.alim.letsconnect.convention.FeatureModuleConventions"
        }
        register("uiModule") {
            id = "buildlogic.uimodule"
            implementationClass = "com.alim.letsconnect.convention.UiModuleConventions"
        }
        register("appModule") {
            id = "buildlogic.appmodule"
            implementationClass = "com.alim.letsconnect.convention.AppModuleConventions"
        }
        register("appFlavors") {
            id = "buildlogic.appFlavors"
            implementationClass = "com.alim.letsconnect.convention.AppFlavorsConventions"
        }
    }
}
