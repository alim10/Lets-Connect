package org.alimapps.letsconnect.core.common.general

import org.alimapps.letsconnect.core.common.state.StateData

@Deprecated("Please consider to use or refactor to [ResponseResult] since its sealed class", replaceWith = ReplaceWith("ResponseResult","com.lean.sehhaty.util.ResponseResult"))
data class Resource<out T>(
    val status: StateData.DataStatus,
    val data: T?,
    val error: ErrorObject?
) {

    companion object {
        fun <T> success(data: T?): Resource<T> {
            return Resource(StateData.DataStatus.SUCCESS, data, null)
        }

        fun <T> error(error: ErrorObject, data: T?): Resource<T> {
            return Resource(StateData.DataStatus.ERROR, data, error)
        }

        fun <T> loading(data: T?): Resource<T> {
            return Resource(StateData.DataStatus.LOADING, data, null)
        }
    }
}

fun ErrorObject?.orDefault() = this ?: ErrorObject.default()