plugins {
    alias(libs.plugins.letsConnect.appDataModule)
    alias(libs.plugins.letsConnect.appFlavors)
}

//android {
//    namespace = "org.alimapps.letsconnect.core.session"
//}

dependencies {
    implementation(libs.sql.cipher)
    implementation(libs.androidx.sqlite)
    implementation(libs.androidx.security.crypto)
}
