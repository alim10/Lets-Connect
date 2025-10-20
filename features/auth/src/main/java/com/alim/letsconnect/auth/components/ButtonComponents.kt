package com.alim.letsconnect.auth.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.alim.letsconnect.core.theme.AppBtnWidth
import com.alim.letsconnect.core.theme.AppHeight
import com.alim.letsconnect.core.theme.ColorAppText
import com.alim.letsconnect.core.theme.ColorWhite

@Composable
fun CustomButtonSmall(value: String) {
    Button(
        onClick = {

        },
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = "Submit",
            style = TextStyle(
                color = ColorWhite, textAlign = TextAlign.Center
            )
        )
    }
}

@Composable
fun CustomButtonElevated(
    textButton: String,
    onClick: () -> Unit
) {
    ElevatedButton(
        modifier = Modifier.size(width = AppBtnWidth.medium, height = AppHeight.h_48),
        onClick = onClick,
//        modifier = Modifier.wrapContentSize().wrapContentHeight()
    ) {
        Text(
            text = textButton,

            style = TextStyle.Default.copy(
                color = ColorWhite, textAlign = TextAlign.Center,
            )

        )
    }
}

@Composable
fun CustomButton(
    textButton: String,
    onClick: () -> Unit
) {
    Button(
        modifier = Modifier.size(width = AppBtnWidth.medium, height = AppHeight.h_48),
        onClick = onClick,
//        modifier = Modifier.wrapContentSize().wrapContentHeight()
    ) {
        Text(
            text = textButton,

            style = TextStyle.Default.copy(
                color = ColorWhite, textAlign = TextAlign.Center,
            )

        )
    }
}

@Composable
fun CustomButtonLarge(value: String) {
    Text(
        text = value,
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 40.dp),
        style = TextStyle(
            fontSize = 24.sp,
            fontWeight = FontWeight.SemiBold,
            fontStyle = FontStyle.Normal
        ),
        color = ColorAppText,
        textAlign = TextAlign.Center
    )
}

@Composable
fun CustomButtonMedium(value: String) {
    Text(
        text = value,
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(),
        style = TextStyle(
            fontSize = 30.sp,
            fontWeight = FontWeight.Medium,
            fontStyle = FontStyle.Normal
        ),
        color = ColorAppText,
        textAlign = TextAlign.Center
    )
}