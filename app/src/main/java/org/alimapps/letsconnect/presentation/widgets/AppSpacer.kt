package org.alimapps.letsconnect.presentation.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import org.alimapps.letsconnect.apps.AppIconSize
import org.alimapps.letsconnect.core.theme.ColorWhite

@Composable
fun AppSpacer(
    modifier: Modifier = Modifier,
    height: Dp = AppIconSize.large) {
    Divider(
        modifier = modifier
            .fillMaxWidth()
            .height(height),
    )
}

@Composable
fun AppSideSpacer(
    modifier: Modifier = Modifier,
    height: Dp = AppIconSize.large) {
    Divider(
        modifier = modifier
            .fillMaxHeight()
            .background(ColorWhite)
            .width(height),
    )
}