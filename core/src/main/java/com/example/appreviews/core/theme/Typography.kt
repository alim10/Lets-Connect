package com.example.appreviews.core.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.example.appreviews.core.AppTextSize
import com.example.appreviews.core.R


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
        fontSize = AppTextSize.appTextLabelLargest
    ),
    headlineMedium = TextStyle(
        color = ColorAppText,
        fontFamily = gothicA1,
        fontWeight = FontWeight.SemiBold,
        fontSize = AppTextSize.appTextLabelLarge
    ),
    headlineSmall = TextStyle(
        color = ColorAppText,
        fontFamily = gothicA1,
        fontWeight = FontWeight.Medium,
        fontSize = AppTextSize.appTextLabelMedium
    ),

    titleLarge = TextStyle(
        color = ColorAppText,
        fontFamily = gothicA1,
        fontWeight = FontWeight.Medium,
        fontSize = AppTextSize.appTextMedium
    ),
    titleMedium = TextStyle(
        color = ColorAppText,
        fontFamily = gothicA1,
        fontWeight = FontWeight.Medium,
        fontSize = AppTextSize.appTextRegular
    ),

    bodyLarge = TextStyle(
        color = ColorAppTextLight,
        fontFamily = gothicA1,
        fontWeight = FontWeight.Normal,
        fontSize = AppTextSize.appTextRegular
    ),
    bodyMedium = TextStyle(
        color = ColorAppTextLight,
        fontFamily = gothicA1,
        fontWeight = FontWeight.Normal,
        fontSize = AppTextSize.appTextLabelRegular,
    ),
    bodySmall = TextStyle(
        color = ColorAppTextLight,
        fontFamily = gothicA1,
        fontWeight = FontWeight.Normal,
        fontSize = AppTextSize.appTextLabelRegular,
    ),
    labelMedium = TextStyle(
        color = ColorAppTextExtraLight,
        fontFamily = gothicA1,
        fontWeight = FontWeight.Thin,
        fontSize = AppTextSize.appTextSmall
    ),
    labelSmall = TextStyle(
        color = ColorAppTextLight,
        fontFamily = gothicA1,
        fontWeight = FontWeight.Normal,
        fontSize = AppTextSize.appTextLabelSmall
    ),


)