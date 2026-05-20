package org.alimapps.letsconnect.core.network.interceptors

import okhttp3.Interceptor
import okhttp3.Response
import org.alimapps.letsconnect.core.network.retrofit.isPartOfCacheable
import java.util.concurrent.TimeUnit

/**
 * overrides [Response] headers to re-write server cache policy.
 */
class RewriteResponseCacheControlInterceptor(
    private val duration: Int = 5,
    private val timeUnit: TimeUnit = TimeUnit.SECONDS,
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        try {
            if (!chain.isCacheable()) return chain.proceed(chain.request())
        } catch (e: Exception) { e.printStackTrace() }
        val maxStale = timeUnit.toSeconds(duration.toLong())
        val originalResponse: Response = chain.proceed(chain.request())
        return originalResponse.newBuilder().header(
            "Cache-Control",
            "public, max-age=$maxStale, max-stale=$maxStale"
        ).build()
    }
}

private fun Interceptor.Chain.isCacheable() = this.request().url.toString().isPartOfCacheable()