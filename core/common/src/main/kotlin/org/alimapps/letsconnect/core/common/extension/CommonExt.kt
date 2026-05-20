package org.alimapps.letsconnect.core.common.extension

fun Double?.orDefault(defaultValue: Double = 0.0): Double = this ?: defaultValue

fun Any?.isNull() = this == null

fun Boolean?.isNullOrFalse() = this == null || this == false

fun Boolean?.isNullOrTrue() = this == null || this == true

fun Boolean?.isTrue() = this != null && this == true

fun Boolean?.isFalse() = this != null && this == false

fun Any?.isNotNull() = this != null