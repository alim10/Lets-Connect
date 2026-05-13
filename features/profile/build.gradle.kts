plugins {
//    alias(libs.plugins.app.letsConnect.uiModule)
    alias(libs.plugins.letsConnect.appFeaturesModule)
    alias(libs.plugins.letsConnect.appFlavors)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "org.alimapps.letsconnect.features.profile"
    compileSdk = 36

    defaultConfig {
        minSdk = 24

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
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

//dependencies {
//    implementation(libs.androidx.constraintlayout)
//    implementation(libs.androidx.compose.constraintlayout)
////    implementation(libs.constraintCompose)
//    implementation(libs.androidx.core.ktx)
//    implementation(libs.androidx.appcompat)
//    implementation(libs.material)
//
//
//    testImplementation(libs.junit)
//    androidTestImplementation(libs.androidx.junit)
//    androidTestImplementation(libs.androidx.espresso.core)
//    androidTestImplementation(platform(libs.androidx.compose.bom))
//    androidTestImplementation(libs.androidx.compose.ui.testing.junit4)
//    debugImplementation(libs.androidx.compose.ui.tooling)
//    debugImplementation(libs.androidx.compose.ui.testing.manifest)
//
//
////    implementation(project(":core"))
//}