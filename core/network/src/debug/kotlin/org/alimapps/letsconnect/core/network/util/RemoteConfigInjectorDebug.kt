package org.alimapps.letsconnect.core.network.util
import android.util.Log
import androidx.compose.ui.util.fastForEach
import com.lean.sehhaty.network.data.local.source.CacheRemoteConfigRepositoryImpl
import javax.inject.Inject
import kotlin.reflect.full.declaredMemberFunctions
import kotlin.reflect.jvm.isAccessible

class RemoteConfigInjectorDebug
@Inject
constructor(
    private val debugRemoteConfig: CacheRemoteConfigRepositoryImpl,
): IRemoteConfigInjectorDebug {

    override fun callForInitialize() {
        Log.e("RemoteConfigInjector", "insertRemoteCacheValues: >>> Debug mode is on")
        insertRemoteCacheValues(debugRemoteConfig)
    }

    private fun insertRemoteCacheValues(repository: CacheRemoteConfigRepositoryImpl) {
        // Filter to only get the functions that start with "get" and exclude the init functions
        val functionsToCall = CacheRemoteConfigRepositoryImpl::class.declaredMemberFunctions
            .filter { it.name.startsWith("get") && it.name !in listOf("initRemoteConfigDefaultFlags", "initRemoteConfig", "getConfigurationValue", "getConfigurationData", "getConfigurationString", "reconfigureFeaturesForUserSegmentation") }

        println("List size is >> ${functionsToCall.size}")

        functionsToCall.fastForEach { function ->
            function.isAccessible = true // Allows access to private/protected functions if needed
            val params = function.parameters
            function.call(repository)
        }
    }
}