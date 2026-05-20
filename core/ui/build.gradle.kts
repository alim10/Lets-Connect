plugins {
    alias(libs.plugins.letsConnect.appFeaturesModule)
    alias(libs.plugins.letsConnect.appFlavors)
}

android {
    namespace = "org.alimapps.letsconnect.core.session"
    ndkVersion = libs.versions.ndkVersion.get()

    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")

        buildConfigField("String", "BASE_URL", "\"https://www.google.com/\"")
        buildConfigField("String", "IMAGE_URL", "\"https://www.google.com/\"")

        externalNativeBuild.cmake.apply {
            cppFlags("-std=c++17")
            // Support for 16 KB page sizes (Android 15+)
            arguments("-DCMAKE_SHARED_LINKER_FLAGS=-Wl,-z,max-page-size=16384")
        }
    }

    externalNativeBuild.cmake.apply {
        path = file("src/main/cpp/CMakeLists.txt")
        version = libs.versions.cMakeVersion.get()
    }

    buildTypes {
        release {
            isMinifyEnabled = false

            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }

        debug {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
}

dependencies {
    implementation(projects.core.database)
    implementation(projects.core.analytics)
    implementation(libs.androidx.core.ktx)
    implementation(libs.timber.lib)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    implementation(platform(libs.firebase.bom))
    implementation(libs.bundles.group.firebase)
    implementation(libs.bundles.work.manager)
    implementation(libs.bundles.group.navigation)
    implementation(libs.bundles.group.network)
    implementation(libs.bundles.group.room)
    implementation(libs.bundles.group.compose.ui)
    implementation(libs.bundles.group.android.ui)

    implementation(libs.accompanist.permissions)
    api(libs.bundles.group.gms.map.location)

    implementation(libs.sql.cipher)
    implementation(libs.androidx.sqlite)
    implementation(libs.androidx.security.crypto)
}
