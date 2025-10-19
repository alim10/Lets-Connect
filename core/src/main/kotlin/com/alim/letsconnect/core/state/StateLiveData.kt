package com.alim.letsconnect.core.state

import androidx.lifecycle.MediatorLiveData
import com.lean.sehhaty.common.general.ErrorObject

class StateLiveData<T> : MediatorLiveData<StateData<T>>() {
    /**
     * Use this to put the Data on a LOADING Status
     */
    fun setLoading(data: T? = null) {
        postValue(StateData<T>().loading(data))
    }

    /**
     * Use this to put the Data on a SUCCESS DataStatus
     * @param data
     */
    fun setSuccess(data: T? = null) {
        postValue(StateData<T>().success(data))
    }

    /**
     * Use this to put the Data on a ERROR DataStatus
     * @param throwable the error to be handled
     */
    fun setError(throwable: ErrorObject?) {
        postValue(StateData<T>().error(throwable!!))
    }
}