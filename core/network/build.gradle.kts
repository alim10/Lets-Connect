plugins {
    alias(libs.plugins.letsConnect.appDataModule)
    alias(libs.plugins.letsConnect.appFlavors)
    alias(libs.plugins.letsConnect.appFlavorsEndPoint)
}

android {
    namespace = "org.alimapps.letsconnect.core.network"

    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
}

dependencies {
    implementation(libs.timber.lib)
    implementation(libs.chucker.debug)

    implementation(libs.bundles.group.network)
    implementation(libs.bundles.group.security)
    implementation(libs.bundles.group.room)

    implementation(projects.core.session)
    implementation(projects.core.common)
    implementation(projects.core.analytics)
    implementation(projects.core.database)
}
