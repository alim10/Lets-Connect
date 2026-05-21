package org.alimapps.letsconnect.core.network.retrofit.utils

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import org.alimapps.letsconnect.core.network.retrofit.RemoteConfigFeaturesConstants
import org.alimapps.letsconnect.core.network.retrofit.RemoteConfigFeaturesConstants.MyHealth.MY_HEALTH_NEPHIS_PARTNER_COUNT
import kotlin.collections.listOf

fun main() {
    // Please add any new object class name here
    val classes = listOf(
        RemoteConfigFeaturesConstants.Dashboard.javaClass.declaredFields,
    )
    val keysList: MutableList<String> = mutableListOf()
    classes.forEach {
        val properties = it
        properties.forEach {
            val value = it[0]
            if (value is String
                && !keysList.contains(value) // NO_DUPLICATION
                && value != "INSTANCE"       // NOT THE CLASS INSTANCE
                && !value.startsWith("com.lean.sehhaty.remoteconfig")       // NOT THE INSTANCE VALUE
            ) {
                keysList.add(value)
            }
        }
    }
    print("keys size= ${keysList.size}")
    println()
    
    val configs: MutableList<FeatureConfigurations> = mutableListOf()
    keysList.forEach {
        configs.add(
            FeatureConfigurations(
                featureKey = it,
                isEnabled = true,
                data = if (it == MY_HEALTH_NEPHIS_PARTNER_COUNT) 40.0 else null
            )
        )
    }
    print(
        Gson()
            .newBuilder()
            .setPrettyPrinting()
            .create()
            .toJson(configs)
    )
}

data class FeatureConfigurations(
    @SerializedName("featureKey") val featureKey: String? = null,
    @SerializedName("enabled") var isEnabled: Boolean? = null,
    @SerializedName("data") val data: Any? = null,
)