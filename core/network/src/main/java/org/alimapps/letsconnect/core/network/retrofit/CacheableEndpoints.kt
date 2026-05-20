package org.alimapps.letsconnect.core.network.retrofit

import org.alimapps.letsconnect.core.network.BuildConfig

/**
 * ADD YOUR ENDPOINT TO THE LIST AND IT WILL BE CACHEABLE
 */
private val cacheableEndpoints = listOf(
    "GET_VISITS",
    "GET_PRESCRIPTIONS",
    "GET_HEALTH_SUMMARY_SERVICES",
)

/**
 * Check if passed url part of cacheable endpoints
 */
fun String.isPartOfCacheable() = cacheableEndpoints.any { this.contains(it) }