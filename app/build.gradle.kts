@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlinKapt)
    alias(libs.plugins.daggerHilt)
    alias(libs.plugins.googleGmsServices)
    alias(libs.plugins.app.letsConnect.flavors)
    alias(libs.plugins.app.letsConnect.refreshDependencies)
}

android {
    namespace = "org.alimapps.letsconnect"
    compileSdk = 35

    defaultConfig {
        applicationId = "com"
        minSdk = 24
//        targetSdk = 35
        versionCode = 4
        versionName = "1.0.4"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }

        ndk {
            debugSymbolLevel = "FULL"
        }
    }
    buildFeatures {
        buildConfig  = true
    }


    buildTypes {
        release {
            isMinifyEnabled = true
            buildConfigField("String", "IMAGE_URL", "\"https://app.karwaty.com/\"")
            buildConfigField("String", "BASE_URL", "\"https://app.karwaty.com/\"")
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }

        debug {
            isMinifyEnabled = false
            isDebuggable = true
            buildConfigField("String", "BASE_URL", "\"https://app.karwaty.com/\"")
            buildConfigField("String", "IMAGE_URL", "\"https://app.karwaty.com/\"")
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }

    }

    productFlavors {
        getByName("letsConnectDev") {
            applicationId = "org.alimapps.letsconnect"
            applicationIdSuffix =".dev"
        }
        getByName("letsConnectQa") {
            applicationId = "org.alimapps.letsconnect"
            applicationIdSuffix =".qa"
        }
        getByName("letsConnectStaging") {
            applicationId = "org.alimapps.letsconnect"
            applicationIdSuffix =".staging"
        }
        getByName("letsConnectProd") {
            applicationId = "org.alimapps.letsconnect"
            applicationIdSuffix =".prod"
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
        viewBinding = true
    }

    composeOptions {
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
    implementation(platform(libs.androidx.compose.bom))
    implementation ("androidx.core:core-splashscreen:1.0.0-beta02")

    implementation(libs.appcompat)
    implementation(libs.constraintlayout)
    implementation(libs.constraintlayoutCore)
//    implementation(libs.constraintCompose)
    implementation(libs.core.ktx)
    implementation(libs.lifecycle.runtime.ktx)
    implementation(libs.activity.compose)
    implementation(libs.ui)
    implementation(libs.ui.graphics)
    implementation(libs.ui.tooling.preview)
    implementation(libs.composeMaterial3)


    implementation(libs.androidx.work.runtime.ktx)
    implementation(libs.androidx.navigation.runtime.android)
    testImplementation(libs.junit)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.espresso.core)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.ui.test.junit4)
    debugImplementation(libs.ui.tooling)
    debugImplementation(libs.ui.test.manifest)


    implementation(libs.viewModel)
    implementation(libs.composeViewModel)
    implementation(libs.composeViewModelRuntime)

    implementation(libs.gsonConverter)
    implementation(libs.retrofit)
    implementation(libs.retrofitConvertorScalor)
    implementation(libs.okhttp)
    implementation(libs.loggingInterceptor)


//    implementation(libs.gms.messaging.ktx)

    implementation(libs.coil)
    implementation(libs.coil.compose)

    implementation(libs.dagger.hilt)
    kapt(libs.dagger.hilt.ompiler)

    implementation(libs.app.update)
    implementation(libs.app.update.ktx)
    implementation(libs.app.review)
    implementation(libs.app.review.ktx)
    implementation(libs.android.play.core)
    implementation(libs.android.play.core)
    implementation(libs.semver.kt)

    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.messaging)
    // Add the dependencies for the Remote Config and Analytics libraries
    // When using the BoM, you don't specify versions in Firebase library dependencies
//    implementation(libs.firebase.config)
    implementation(libs.firebase.analytics)
    // Add the dependencies for the In-App Messaging and Analytics libraries
    // When using the BoM, you don't specify versions in Firebase library dependencies
    implementation(libs.firebase.inappmessaging.display)

    implementation(project(":core"))
//    implementation(project(":auth"))
//    implementation(project(":profile"))
}