plugins {
    alias(libs.plugins.letsConnect.appFeaturesModule)
    alias(libs.plugins.letsConnect.appFlavors)
}

android {
    namespace = "org.alimapps.letsconnect.features.chats"
}

dependencies {
    implementation(libs.androidx.compose.foundation.layout)
    implementation(projects.core.common)
    implementation(projects.core.network)
    implementation(projects.core.remoteConfig)
    implementation(projects.core.ui)
    implementation(projects.core.analytics)
    implementation(projects.features.profile)
}
