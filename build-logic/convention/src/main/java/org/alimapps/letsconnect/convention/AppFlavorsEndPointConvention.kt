package org.alimapps.letsconnect.convention

import com.android.build.gradle.BaseExtension
import org.gradle.api.Plugin
import org.gradle.api.Project

class AppFlavorsEndPointConvention : Plugin<Project> {
    override fun apply(target: Project) {
        val baseUrl = "BASE_URL"
        val extension = target.extensions.getByName("android")
        if (extension is BaseExtension) {
            extension.apply {
                flavorDimensions("build", "vendor")

                productFlavors {
                    getByName("appQa") {
                        buildConfigField("String", "APP_NAME", "\"SHComposeDemo\"")
                        buildConfigField("String", "BASE_URL", "\"http://213.136.192.12/\"")

                        buildConfigField("String", "BASE_URL", "\"api/signin\"")
                        buildConfigField("String", "OTP_SIGNIN", "\"api/signin/OtpSignIn\"")
                        buildConfigField("String", "BASE_URL", "\"api/utilities/bannerList\"")
                        buildConfigField("String", "BASE_URL", "\"api/store/getLuckyDraw\"")
                        buildConfigField("String", "BASE_URL", "\"api/football/sports/getSeries\"")
                        buildConfigField("Boolean", "ENABLE_MOCK_LOCATION", "true")

                    }
                    getByName("appDev") {
                        buildConfigField("String", "APP_NAME", "\"SHComposeDemo\"")
                        buildConfigField("String", "BASE_URL", "\"http://213.136.192.12/\"")

                        buildConfigField("String", "BASE_URL", "\"api/signin\"")
                        buildConfigField("String", "OTP_SIGNIN", "\"api/signin/OtpSignIn\"")
                        buildConfigField("String", "BASE_URL", "\"api/utilities/bannerList\"")
                        buildConfigField("String", "BASE_URL", "\"api/store/getLuckyDraw\"")
                        buildConfigField("String", "BASE_URL", "\"api/football/sports/getSeries\"")
                        buildConfigField("Boolean", "ENABLE_MOCK_LOCATION", "true")

                    }
                    getByName("appStaging") {
                        buildConfigField("String", "APP_NAME", "\"SHComposeDemo\"")
                        buildConfigField("String", "BASE_URL", "\"http://213.136.192.12/\"")

                        buildConfigField("String", "BASE_URL", "\"api/signin\"")
                        buildConfigField("String", "OTP_SIGNIN", "\"api/signin/OtpSignIn\"")
                        buildConfigField("String", "BASE_URL", "\"api/utilities/bannerList\"")
                        buildConfigField("String", "BASE_URL", "\"api/store/getLuckyDraw\"")
                        buildConfigField("String", "BASE_URL", "\"api/football/sports/getSeries\"")
                        buildConfigField("Boolean", "ENABLE_MOCK_LOCATION", "true")

                    }
                    getByName("appProd") {
                        buildConfigField("String", "APP_NAME", "\"SHComposeDemo\"")
                        buildConfigField("String", "BASE_URL", "\"http://213.136.192.12/\"")

                        buildConfigField("String", "BASE_URL", "\"api/signin\"")
                        buildConfigField("String", "OTP_SIGNIN", "\"api/signin/OtpSignIn\"")
                        buildConfigField("String", "BASE_URL", "\"api/utilities/bannerList\"")
                        buildConfigField("String", "BASE_URL", "\"api/store/getLuckyDraw\"")
                        buildConfigField("String", "BASE_URL", "\"api/football/sports/getSeries\"")
                        buildConfigField("Boolean", "ENABLE_MOCK_LOCATION", "true")

                    }
                }
            }
        }
    }
}