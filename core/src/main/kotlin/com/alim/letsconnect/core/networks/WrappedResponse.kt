package com.alim.letsconnect.core.networks

import android.text.TextUtils
import com.alim.letsconnect.core.utils.Const
import com.google.gson.annotations.SerializedName

data class WrappedListResponse<T> (
    var code: Int,
    @SerializedName("message") var message: HashMap<String?, String>?,
    @SerializedName("status") var status: Boolean,
    @SerializedName("data") var data: List<T>? = null
)
data class WrappedResponse<T> (
    var code: Int,
    @SerializedName("message") var message: HashMap<String?, String>?,
    @SerializedName("status") var status: Boolean,
    @SerializedName("data") var data: T? = null
) {
    fun getLocalizedMessage(): String {
        if (message != null && message!!.containsKey(Const.SELECTED_LANGUAGE)) {
            val str = message!![Const.SELECTED_LANGUAGE]
            if (!TextUtils.isEmpty(str)) {
                return str?: Const.EMPTY
            }
        }
        return Const.EMPTY
    }
}

/*data class WrappedListResponse<T> (
    var code: Int,
    @SerializedName("message") var message: HashMap<String?, String>?,
    @SerializedName("status") var status: Boolean,
    @SerializedName("data") var data: List<T>? = null
)
data class WrappedResponse<T> (
    var code: Int,
    @SerializedName("message") var message: HashMap<String?, String>?,
    @SerializedName("status") var status: Boolean,
    @SerializedName("data") var data: T? = null
) {
    fun getLocalizedMessage(): String {
        if (message != null && message!!.containsKey(Const.SELECTED_LANGUAGE)) {
            val str = message!![Const.SELECTED_LANGUAGE]
            if (!TextUtils.isEmpty(str)) {
                return str?: Const.EMPTY
            }
        }
        return Const.EMPTY
    }
}*/

