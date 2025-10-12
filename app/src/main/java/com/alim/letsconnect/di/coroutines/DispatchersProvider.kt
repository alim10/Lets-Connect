package com.alim.letsconnect.di.coroutines

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

/**
 * Created by Ahmed Ibrahim on 11,January,2022
 */
interface DispatchersProvider {
    fun io(): CoroutineDispatcher = Dispatchers.IO
}