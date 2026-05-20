package com.lean.sehhaty.network.userToken

import androidx.annotation.Keep

@Keep
data class RefreshTokenResponse(val access_token: String, val refresh_token: String)