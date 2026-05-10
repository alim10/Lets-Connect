plugins {
    alias(libs.plugins.letsConnect.appFeaturesModule)
    alias(libs.plugins.letsConnect.appFlavors)
}
//apply(from = "$rootProject.projectDir/app-flavors.gradle")

android {
    namespace = "org.alimapps.letsconnect.core.common"
    compileSdk = 36

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildFeatures {
        buildConfig = true
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            buildConfigField("String", "BASE_URL", "\"https://app.karwaty.com/\"")
            buildConfigField("String", "IMAGE_URL", "\"https://app.karwaty.com/\"")
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }

        debug {
            isMinifyEnabled = false
            buildConfigField("String", "BASE_URL", "\"https://app.karwaty.com/\"")
            buildConfigField("String", "IMAGE_URL", "\"https://app.karwaty.com/\"")
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

dependencies {
    implementation(libs.androidx.core)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.constraintlayout)
//    implementation(libs.androidx.constraintlayoutCore)
//    implementation(libs.constraintCompose)
    implementation(libs.material)
    implementation(libs.androidx.lifecycle.runtime.ktx)

    implementation(platform(libs.androidx.compose.bom))

    implementation(libs.androidx.compose.activity)
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.graphics)
    implementation(libs.androidx.compose.preview)
    implementation(libs.androidx.compose.material3)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.testing.junit4)
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.testing.manifest)

//    implementation(libs.dagger.hilt.android)
//    kapt(libs.dagger.hilt.android.compiler)

//    implementation(libs.gsonConverter)
    implementation(libs.google.gson)
    implementation(libs.retrofit)
    implementation(libs.retrofit.convertor.scalor)
    implementation(libs.okhttp)
    implementation(libs.okhttp.logging.interceptor)
    implementation(libs.timber.lib)

//    implementation(platform(libs.firebase.bom))
//    implementation(libs.firebase.remote.config)
//    implementation(libs.firebase.analytics)
}