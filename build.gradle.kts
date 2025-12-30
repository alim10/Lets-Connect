// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.kotlin.android) apply false  // alimapps kotlin
    alias(libs.plugins.kotlin.compose) apply false
    alias(libs.plugins.kotlinKapt) apply false  // alimapps kotlin_kapt
    alias(libs.plugins.googleGmsServices) apply false
    alias(libs.plugins.firebaseCrashlytics) apply false
    alias(libs.plugins.daggerHilt) apply false
    alias(libs.plugins.ksp) apply false
}
//true // Needed to make the Suppress annotation work for the plugins block