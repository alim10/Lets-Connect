package org.alimapps.letsconnect.core.network.clients
import retrofit2.Retrofit
import javax.inject.Inject

class RetrofitClient
@Inject
constructor(
    private val retrofit: Retrofit
) {
    fun <S> getService(service: Class<S>): S = retrofit.create(service)
}
