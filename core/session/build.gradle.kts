plugins {
    alias(libs.plugins.letsConnect.appFeaturesModule)
    alias(libs.plugins.letsConnect.appFlavors)
}

dependencies {
    implementation(libs.sql.cipher)
    implementation(libs.androidx.sqlite)
    implementation(libs.androidx.security.crypto)
}
