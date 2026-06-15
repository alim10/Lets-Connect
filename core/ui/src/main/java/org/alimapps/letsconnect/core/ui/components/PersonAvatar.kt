package org.alimapps.letsconnect.core.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage

@Composable
fun ChatAvatar(
    name: String,
    imageUrl: String?,
    modifier: Modifier = Modifier,
    size: Dp = 40.dp
) {
    val backgroundColor = remember(name) {
        val colors = listOf(
            Color(0xFFEF5350), Color(0xFFEC407A), Color(0xFFAB47BC),
            Color(0xFF7E57C2), Color(0xFF5C6BC0), Color(0xFF42A5F5),
            Color(0xFF29B6F6), Color(0xFF26C6DA), Color(0xFF26A69A),
            Color(0xFF66BB6A), Color(0xFF9CCC65), Color(0xFFD4E157),
            Color(0xFFFFEE58), Color(0xFFFFCA28), Color(0xFFFFA726),
            Color(0xFFFF7043), Color(0xFF8D6E63), Color(0xFFBDBDBD), Color(0xFF78909C)
        )
        colors[name.hashCode().let { if (it < 0) -it else it } % colors.size]
    }

    val initial = remember(name) {
        name.firstOrNull()?.uppercase() ?: "?"
    }

    Box(
        modifier = modifier
            .size(size)
            .clip(CircleShape)
            .background(backgroundColor),
        contentAlignment = Alignment.Center
    ) {
        if (!imageUrl.isNullOrBlank()) {
            AsyncImage(
                model = imageUrl,
                contentDescription = name,
                modifier = Modifier.size(size).clip(CircleShape),
                contentScale = ContentScale.Crop,
                onError = { /* Fail silently, will show initial */ }
            )
        } else {
            Text(
                text = initial,
                color = Color.White,
                fontSize = (size.value * 0.4).sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}
