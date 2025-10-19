package com.alim.letsconnect.core.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.alim.letsconnect.core.R


val gothicA1 = FontFamily(
    listOf(
        Font(R.font.sanfrancisco_thin, FontWeight.Thin),
        Font(R.font.sanfrancisco_regular, FontWeight.Normal),
        Font(R.font.sanfrancisco_medium, FontWeight.Medium),
        Font(R.font.sanfrancisco_bold, FontWeight.SemiBold),
        Font(R.font.sanfrancisco_bold, FontWeight.Bold),
    )
)

// Set of Material typography styles to start with
val Typography = Typography(
    headlineLarge = TextStyle(
        color = ColorAppText,
        fontFamily = gothicA1,
        fontWeight = FontWeight.Bold,
        fontSize = AppTextSize.textLabelLarge_I
    ),
    headlineMedium = TextStyle(
        color = ColorAppText,
        fontFamily = gothicA1,
        fontWeight = FontWeight.SemiBold,
        fontSize = AppTextSize.textLabelLarge
    ),
    headlineSmall = TextStyle(
        color = ColorAppText,
        fontFamily = gothicA1,
        fontWeight = FontWeight.Medium,
        fontSize = AppTextSize.textLabelMedium
    ),

    titleLarge = TextStyle(
        color = ColorAppText,
        fontFamily = gothicA1,
        fontWeight = FontWeight.Medium,
        fontSize = AppTextSize.textMedium
    ),
    titleMedium = TextStyle(
        color = ColorAppText,
        fontFamily = gothicA1,
        fontWeight = FontWeight.Medium,
        fontSize = AppTextSize.textRegular
    ),

    bodyLarge = TextStyle(
        color = ColorAppTextLight,
        fontFamily = gothicA1,
        fontWeight = FontWeight.Normal,
        fontSize = AppTextSize.textRegular
    ),
    bodyMedium = TextStyle(
        color = ColorAppTextLight,
        fontFamily = gothicA1,
        fontWeight = FontWeight.Normal,
        fontSize = AppTextSize.textLabelRegular,
    ),
    bodySmall = TextStyle(
        color = ColorAppTextLight,
        fontFamily = gothicA1,
        fontWeight = FontWeight.Normal,
        fontSize = AppTextSize.textLabelRegular,
    ),
    labelMedium = TextStyle(
        color = ColorAppTextExtraLight,
        fontFamily = gothicA1,
        fontWeight = FontWeight.Thin,
        fontSize = AppTextSize.textSmall
    ),
    labelSmall = TextStyle(
        color = ColorAppTextLight,
        fontFamily = gothicA1,
        fontWeight = FontWeight.Normal,
        fontSize = AppTextSize.textLabelSmall
    ),


)