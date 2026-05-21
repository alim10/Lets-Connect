package org.alimapps.letsconnect.core.network.retrofit.exceptions
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.catch
import org.alimapps.letsconnect.core.common.general.ErrorObject

fun <T> Flow<T>.catchError(
    action: suspend FlowCollector<T>.(cause: ErrorObject) -> Unit
): Flow<T> = catch { action(GeneralExceptionHandler.handleAllKindOfExceptions(response = Gson().toJson(it), code = null, error = it)) }