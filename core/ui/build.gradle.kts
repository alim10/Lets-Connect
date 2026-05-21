plugins {
    alias(libs.plugins.letsConnect.appUiModule)
    alias(libs.plugins.letsConnect.appFlavors)
}

dependencies {
    implementation(projects.core.common)
    implementation(projects.core.session)
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
    implementation(libs.bundles.group.compose.navigation)
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
    implementation(libs.lottie)
    implementation(libs.lottie.compose)
}
