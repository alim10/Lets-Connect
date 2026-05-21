plugins {
    alias(libs.plugins.letsConnect.appHost)
    alias(libs.plugins.google.secrets)
    alias(libs.plugins.letsConnect.appFlavors)
    alias(libs.plugins.letsConnect.appFlavorsEndPoint)
    alias(libs.plugins.letsConnect.appRefreshDependencies)
}

android {

    ndkVersion = libs.versions.ndkVersion.get()

    defaultConfig {
        multiDexEnabled = true
        testInstrumentationRunner = "com.example.shcomposedemo.HiltTestRunner"

        externalNativeBuild.cmake.apply {
            libs.versions.cMakeVersion.get()
            // Magic flag for 16 KB alignment
            arguments += "-DCMAKE_SHARED_LINKER_FLAGS=-Wl,-z,max-page-size=16384"
        }
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
        jniLibs.useLegacyPackaging = false
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        debug { isMinifyEnabled = false }
    }
}

dependencies {
    implementation(projects.core.analytics)
    implementation(projects.core.session)
    implementation(projects.core.common)
    implementation(projects.core.network)
    implementation(projects.features.profile)
    implementation(projects.features.chats)

    implementation(libs.maps.compose)
    implementation(libs.lottie.compose)
    implementation(libs.accompanist.permissions)
    implementation(libs.bundles.group.navigation)
    implementation(libs.bundles.group.network)
    implementation(libs.bundles.work.manager)
    implementation(libs.bundles.group.accompanist.pager)
    implementation(platform(libs.firebase.bom))
    implementation(libs.bundles.group.firebase)
    implementation(libs.bundles.group.gms.map.location)
    implementation(libs.bundles.group.google.play.update)

    implementation(libs.sql.cipher)
    implementation(libs.androidx.security.crypto)


    implementation(group = "net.zetetic", name = "sqlcipher-android", version = "4.11.0")
    implementation(group = "androidx.sqlite", name = "sqlite", version = "2.2.0")
}