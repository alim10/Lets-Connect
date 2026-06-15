plugins {
    alias(libs.plugins.letsConnect.appDataModule)
    alias(libs.plugins.letsConnect.appFlavors)
    alias(libs.plugins.letsConnect.appFlavorsEndPoint)
}

android {
    namespace = "org.alimapps.letsconnect.core.remoteconfig"
}

dependencies {
    implementation(projects.core.common)
    implementation(projects.core.database)
    implementation(projects.core.analytics)
    implementation(projects.core.session)
    implementation(projects.core.network)
    
    implementation(libs.kotlin.reflect)
    implementation(libs.timber.lib)

    implementation(platform(libs.firebase.bom))
    implementation(libs.bundles.group.firebase)
    implementation(libs.bundles.work.manager)
    implementation(libs.bundles.group.navigation)
    implementation(libs.bundles.group.network)
    implementation(libs.bundles.group.room)
    implementation(libs.bundles.group.compose.ui)

    implementation(libs.accompanist.permissions)
    api(libs.bundles.group.gms.map.location)

    implementation(libs.sql.cipher)
    implementation(libs.androidx.sqlite)
    implementation(libs.androidx.security.crypto)
}
