package org.alimapps.letsconnect.core.common.nativeLib

object Secrets {
    init {
        System.loadLibrary("native-lib")
    }

    external fun baseUrl(): String
    external fun apiKey(): String
    external fun encryptionKey(): String
    external fun encryptionIVKey(): String
    external fun xApplicationHeader(): String
    external fun xApplicationClientHeader(): String
    external fun certificatePinning(): String
    external fun databaseEncryptionKey(): String
    external fun firebaseApiKey(): String
    external fun userAgentKey(): String
    external fun telehealthSecretKey(): String
    external fun amplitudeApiKey(): String
    external fun certificatePinner(): String
}