plugins {
    alias(libs.plugins.letsConnect.appFeaturesModule)
    alias(libs.plugins.letsConnect.appFlavors)
}

android {
    namespace = "org.alimapps.letsconnect.core.data"

    ndkVersion = libs.versions.ndkVersion.get()

//    defaultConfig {
//        externalNativeBuild.cmake.apply {
//            cppFlags("-std=c++17")
//            // Support for 16 KB page sizes (Android 15+)
//            arguments("-DCMAKE_SHARED_LINKER_FLAGS=-Wl,-z,max-page-size=16384")
//        }
//    }
//
//    externalNativeBuild.cmake.apply {
//        path = file("src/main/cpp/CMakeLists.txt")
//        version = libs.versions.cMakeVersion.get()
//    }
}

dependencies {
    implementation(projects.core.common)
//    implementation(projects.core.network)
    implementation(projects.core.analytics)

    implementation(libs.androidx.core.ktx)
    implementation(libs.timber.lib)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    implementation(libs.bundles.group.navigation)
    implementation(libs.bundles.group.network)
    implementation(libs.bundles.group.room)
    implementation(libs.bundles.group.security)
    implementation(libs.accompanist.permissions)
    api(libs.bundles.group.gms.map.location)

    implementation(libs.sql.cipher)
    implementation(libs.androidx.sqlite)
    implementation(libs.bundles.group.security)


//    implementation(group = "net.zetetic", name = "sqlcipher-android", version = "4.16.0")
//    implementation(group = "androidx.sqlite", name = "sqlite", version = "2.6.2")

}