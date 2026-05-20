package org.alimapps.letsconnect.core.remoteconfig.model

import com.google.gson.annotations.SerializedName

data class FeatureConfigurations(
    @SerializedName("featureKey") val featureKey: String? = null,
    @SerializedName("enabled") var isEnabled: Boolean? = null,
    @SerializedName("data") val data: Any? = null,
)

