plugins {
    alias(libs.plugins.letsConnect.appFeaturesModule)
    alias(libs.plugins.letsConnect.appFlavors)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "org.alimapps.letsconnect.features.profile"
}

dependencies {
    implementation(projects.core.common)
}