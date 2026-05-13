//plugins {
//    alias(libs.plugins.android.library)
//    alias(libs.plugins.jetbrains.kotlin.android)
//}

plugins {
    alias(libs.plugins.letsConnect.appFeaturesModule)
    alias(libs.plugins.letsConnect.appFlavors)
    alias(libs.plugins.letsConnect.appFlavorsEndPoint)
}
android {
    namespace = "org.alimapps.letsconnect.features.home"
}
//
//android {
//    namespace = "org.alimapps.letsconnect.features.home"
//    compileSdk = 35
//
//    defaultConfig {
//        minSdk = 26
//
//        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
//        consumerProguardFiles("consumer-rules.pro")
//    }
//
//    buildTypes {
//        release {
//            isMinifyEnabled = false
//            proguardFiles(
//                getDefaultProguardFile("proguard-android-optimize.txt"),
//                "proguard-rules.pro"
//            )
//        }
//    }
//    compileOptions {
//        sourceCompatibility = JavaVersion.VERSION_11
//        targetCompatibility = JavaVersion.VERSION_11
//    }
//}
//
//dependencies {
//    implementation(libs.androidx.core.ktx)
//    implementation(libs.androidx.appcompat)
//    implementation(libs.material)
////    testImplementation(libs.junit)
////    androidTestImplementation(libs.androidx.test.ext.junit)
////    androidTestImplementation(libs.espresso.core)
//
//    testImplementation(libs.junit)
//    androidTestImplementation(libs.androidx.junit)
//    androidTestImplementation(libs.androidx.espresso.core)
//    androidTestImplementation(platform(libs.androidx.compose.bom))
//    androidTestImplementation(libs.androidx.compose.ui.testing.junit4)
//    debugImplementation(libs.androidx.compose.ui.tooling)
//    debugImplementation(libs.androidx.compose.ui.testing.manifest)
//}