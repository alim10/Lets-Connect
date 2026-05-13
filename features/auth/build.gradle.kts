@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {

    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt.android)
//    alias(libs.plugins.googlePlayServices)
//    alias(libs.plugins.firebaseCrashlytics)
//    alias(libs.plugins.app.letsConnect.featuresModule)
    alias(libs.plugins.letsConnect.appFlavors)
    alias(libs.plugins.kotlin.compose)
}


android {
    namespace = "org.alimapps.letsconnect.features.auth"
    compileSdk = 35

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
        vectorDrawables {
            useSupportLibrary = true
        }
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
    buildFeatures {
        compose = true
    }
    composeOptions {
//        kotlinCompilerExtensionVersion = "1.5.1"
        kotlinCompilerExtensionVersion = "1.4.3"
//        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)

    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics.android)
    implementation(libs.androidx.compose.ui.tooling.preview.android)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.compose.activity)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.material3)
//    implementation(libs.androidx.storage)
//    implementation(libs.engage.core)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.compose.constraintlayout)

//    implementation(libs.constraintCompose)
//    implementation(libs.core)


    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.analytics)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.compose)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.testing.junit4)
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.testing.manifest)

    implementation(libs.hilt.android)
    ksp(libs.hilt.android.compiler)

//    implementation(libs.gsonConverter)
//    implementation(libs.retrofit)
//    implementation(libs.retrofitConvertorScalor)
//    implementation(libs.okhttp)
    implementation(libs.bundles.group.network)

    implementation("org.github.JoelKanyi:KomposeCountryCodePicker:1.0.6")
    implementation("org.google.accompanist:accompanist-systemuicontroller:0.23.1")
}