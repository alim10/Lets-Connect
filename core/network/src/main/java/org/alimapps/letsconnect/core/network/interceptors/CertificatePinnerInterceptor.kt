package org.alimapps.letsconnect.core.network.interceptors

import okhttp3.CertificatePinner
import org.alimapps.letsconnect.core.data.nativeLib.Secrets

object CertificatePinnerInterceptor {
    fun intercept(): CertificatePinner {
        return CertificatePinner.Builder()
            .add(
                "letsConnect.sa",
                Secrets.certificatePinner(),
            )
            .add(
                "api.letsConnect.sa",
                Secrets.certificatePinner(),
            ).build()
    }
}
