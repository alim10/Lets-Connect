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
    implementation(projects.core.analytics)
    implementation(projects.core.database)
    implementation(libs.timber.lib)
    implementation(libs.bundles.group.network)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)



//    dawiProdApi(libs.chucker.release)
////    dawiProdApi(libs.chucker.debug)
//    dawiDevApi(libs.chucker.debug)
//    dawiQaApi(libs.chucker.debug)
    implementation(libs.chucker.debug)
}
