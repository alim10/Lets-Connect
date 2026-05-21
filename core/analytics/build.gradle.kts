plugins {
    alias(libs.plugins.letsConnect.appFeaturesModule)
    alias(libs.plugins.letsConnect.appFlavors)
}

android.apply {  namespace = "org.alimapps.letsconnect.core.analytics" }

dependencies {
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    implementation(platform(libs.firebase.bom))
    implementation(libs.bundles.group.firebase)
    implementation(libs.amplitude.analytics)
}
/*


plugins {
    alias(libs.plugins.android.library)
}

android {
    namespace = "org.alimapps.letsconnect.core.analytics"
    compileSdk {
        version = release(37)
    }

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

}

dependencies {
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.core.ktx)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.junit)
}*/
