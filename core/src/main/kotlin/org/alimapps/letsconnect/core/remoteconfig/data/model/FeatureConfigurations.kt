package org.alimapps.letsconnect.core.remoteconfig.data.model

import com.google.gson.annotations.SerializedName

data class FeatureConfigurations(
    @SerializedName("featureKey") val featureKey: String? = null,
    @SerializedName("enabled") val isEnabled: Boolean? = null,
    @SerializedName("data") val data: Any? = null,
)

