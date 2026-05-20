package org.alimapps.letsconnect.core.network.interceptors

import com.lean.sehhaty.network.data.local.model.CacheCurlModel
import com.lean.sehhaty.network.data.local.source.IDeveloperOptionsCache
import com.lean.sehhaty.network.util.CurlPrinter
import com.lean.sehhaty.utility.utils.di.coroutines.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.Interceptor
import okhttp3.Interceptor.Chain
import okhttp3.Request
import okhttp3.Response
import okio.Buffer
import java.io.IOException
import java.nio.charset.Charset
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CurlLoggerInterceptor
@Inject
constructor(
    private val cache: IDeveloperOptionsCache,
): Interceptor {
    private var curlCommandBuilder: StringBuilder? = null
    private val charUTF8 = Charset.forName("UTF-8")
    private var tag: String? = "CurlLoggerInterceptor"

    init {
        GlobalScope.launch { cache.deleteCachedCurlRequests() }
    }

    override fun intercept(chain: Chain): Response {
        val request: Request = chain.request()
        curlCommandBuilder = StringBuilder("")
        // add cURL command
        curlCommandBuilder?.append("cURL ")
        curlCommandBuilder?.append("-X ")
        // add method
        curlCommandBuilder?.append(request.method.uppercase() + " ")
        // adding headers
        for (headerName in request.headers.names()) {
            request.headers.get(headerName)?.let { addHeader(headerName, it) }
        }
        
        // adding request body
        val requestBody = request.body
        if (request.body != null) {
            val buffer = Buffer()
            requestBody?.writeTo(buffer)
            var charset = charUTF8
            val contentType = requestBody?.contentType()
            if (contentType != null) {
                addHeader("Content-Type", request.body?.contentType().toString())
                charset = contentType.charset(charUTF8)
                curlCommandBuilder?.append(" -d '" + buffer.readString(charset) + "'")
            }
        }
        
        // add request URL
        curlCommandBuilder?.append(" \"" + request.url.toString() + "\"")
        curlCommandBuilder?.append(" -L")
        GlobalScope.launch {
            val curl = CurlPrinter.print(tag, request.url.toString(), curlCommandBuilder.toString())
            cache.insertCacheCurlModel(
                CacheCurlModel(url = curl.first, allCurl = curl.second)
            )
        }
        return chain.proceed(request)
    }
    
    private fun addHeader(headerName: String, headerValue: String) {
        curlCommandBuilder?.append("-H \"$headerName: $headerValue\" ")
    }
}